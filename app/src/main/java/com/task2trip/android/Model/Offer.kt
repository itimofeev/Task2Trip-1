package com.task2trip.android.Model

import android.os.Parcel
import android.os.Parcelable
import com.task2trip.android.Model.User.UserImpl

data class Offer(val id: String,
                 val comment: String,
                 val price: Int,
                 val user: UserImpl,
                 val createTime: String,
                 val chosenTime: String): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString(), parcel.readString(), parcel.readInt(),
        parcel.readParcelable(UserImpl::class.java.classLoader), parcel.readString(), parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        with(parcel) {
            writeString(id)
            writeString(comment)
            writeInt(price)
            writeParcelable(user, flags)
            writeString(createTime)
            writeString(chosenTime)
        }
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