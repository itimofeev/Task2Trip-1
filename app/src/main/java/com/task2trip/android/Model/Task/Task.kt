package com.task2trip.android.Model.Task

import android.os.Parcel
import android.os.Parcelable

data class Task(val id: String,
                val name: String,
                val description: String,
                val createTime: String,
                val fromDate: String,
                val toDate: String,
                val budgetEstimate: Int,
                val status: String,
                val newDate: String,
                val inProgressTime: String,
                val finishedTime: String,
                val canceledTime: String,
                val category: TaskCategory): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(),
        parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(),
        parcel.readString(), parcel.readString(), parcel.readString(),
        parcel.readParcelable(TaskCategory::class.java.classLoader))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        with(parcel) {
            writeString(id)
            writeString(name)
            writeString(description)
            writeString(createTime)
            writeString(fromDate)
            writeString(toDate)
            writeInt(budgetEstimate)
            writeString(status)
            writeString(newDate)
            writeString(inProgressTime)
            writeString(finishedTime)
            writeString(canceledTime)
            writeParcelable(category, flags)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Task> {
        override fun createFromParcel(parcel: Parcel): Task {
            return Task(parcel)
        }

        override fun newArray(size: Int): Array<Task?> {
            return arrayOfNulls(size)
        }
    }
}