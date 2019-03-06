package com.task2trip.android.Model.Task

import android.os.Parcel
import android.os.Parcelable
import com.task2trip.android.Model.User.UserImpl

data class Task(val id: String,
                val name: String,
                val description: String,
                val createTime: String,
                val fromDate: String,
                val toDate: String,
                val budgetEstimate: Int,
                val status: String,
                val newTime: String,
                val inProgressTime: String,
                val finishedTime: String?,
                val canceledTime: String?,
                val category: TaskCategory,
                val user: UserImpl): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(TaskCategory::class.java.classLoader),
        parcel.readParcelable(UserImpl::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(createTime)
        parcel.writeString(fromDate)
        parcel.writeString(toDate)
        parcel.writeInt(budgetEstimate)
        parcel.writeString(status)
        parcel.writeString(newTime)
        parcel.writeString(inProgressTime)
        parcel.writeString(finishedTime)
        parcel.writeString(canceledTime)
        parcel.writeParcelable(category, flags)
        parcel.writeParcelable(user, flags)
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