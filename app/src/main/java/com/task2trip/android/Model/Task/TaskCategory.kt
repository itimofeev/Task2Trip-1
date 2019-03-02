package com.task2trip.android.Model.Task

import android.os.Parcel
import android.os.Parcelable

data class TaskCategory(val id: String,
                        val key: String,
                        val defaultValue: String,
                        var imageUrl: String,
                        var isSelected: Boolean): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(key)
        parcel.writeString(defaultValue)
        parcel.writeString(imageUrl)
        parcel.writeByte(if (isSelected) 1 else 0)
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