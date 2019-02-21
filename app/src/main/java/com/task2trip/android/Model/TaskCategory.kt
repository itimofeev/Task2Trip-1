package com.task2trip.android.Model

import android.os.Parcel
import android.os.Parcelable

data class TaskCategory(val id: String,
                        val key: String,
                        val defaultValue: String,
                        var imageUrl: String): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        with(parcel) {
            writeString(id)
            writeString(key)
            writeString(defaultValue)
            writeString(imageUrl)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TaskCategory> {
        override fun createFromParcel(parcel: Parcel): TaskCategory {
            return TaskCategory(parcel)
        }

        override fun newArray(size: Int): Array<TaskCategory?> {
            return arrayOfNulls(size)
        }
    }
}