package com.task2trip.android.Model

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.NonNull

class LocalStoreManager(context: Context) {
    private val settingFile: String = "LocalStoreManager"
    var pref: SharedPreferences

    init {
        pref = context.getSharedPreferences(settingFile, Context.MODE_PRIVATE)
    }

    inline fun <reified T : Any> get(key: String, @NonNull defaultValue: T? = null): T? {
        return pref[key, defaultValue]
    }

    fun set(key: String, value: Any?) {
        pref[key] = value
    }
}

private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
    val editor = this.edit()
    operation(editor)
    editor.apply()
}

operator fun SharedPreferences.set(key: String, value: Any?) {
    when (value) {
        is String? -> edit { it.putString(key, value) }
        is Int -> edit { it.putInt(key, value) }
        is Boolean -> edit { it.putBoolean(key, value) }
        is Float -> edit { it.putFloat(key, value) }
        is Long -> edit { it.putLong(key, value) }
        else -> throw UnsupportedOperationException("Not yet implemented (SharedPreferences)")
    }
}

/**
 * finds value on given key.
 * [T] is the type of value
 * @param defaultValue optional default value - will take null for strings, false for bool and -1 for numeric values if [defaultValue] is not specified
 */
inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
    return when (T::class) {
        String::class -> getString(key, defaultValue as? String) as T?
        Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
        Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
        Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
        Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
        else -> throw UnsupportedOperationException("Not yet implemented (SharedPreferences)")
    }
}