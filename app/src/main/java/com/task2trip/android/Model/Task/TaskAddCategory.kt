package com.task2trip.android.Model.Task

import android.os.Parcel
import android.os.Parcelable

data class TaskAddCategory(val id: Long,
                           val categoryImage: String,
                           val categoryName: String,
                           val categoryDescription: String) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        with(parcel) {
            writeLong(id)
            writeString(categoryImage)
            writeString(categoryName)
            writeString(categoryDescription)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TaskAddCategory> {
        override fun createFromParcel(parcel: Parcel): TaskAddCategory {
            return TaskAddCategory(parcel)
        }

        override fun newArray(size: Int): Array<TaskAddCategory?> {
            return arrayOfNulls(size)
        }
    }
}