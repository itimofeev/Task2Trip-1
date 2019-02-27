package com.task2trip.android.Model

import android.os.Parcel
import android.os.Parcelable
import com.task2trip.android.Model.Task.Task
import com.task2trip.android.Model.User.UserImpl

data class Offer(val id: String,
                 val comment: String,
                 val price: Int,
                 val user: UserImpl,
                 val createTime: String,
                 val chosenTime: String,
                 val task: Task): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readParcelable(UserImpl::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Task::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(comment)
        parcel.writeInt(price)
        parcel.writeParcelable(user, flags)
        parcel.writeString(createTime)
        parcel.writeString(chosenTime)
        parcel.writeParcelable(task, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Offer> {
        override fun createFromParcel(parcel: Parcel): Offer {
            return Offer(parcel)
        }

        override fun newArray(size: Int): Array<Offer?> {
            return arrayOfNulls(size)
        }
    }
}