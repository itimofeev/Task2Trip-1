package com.task2trip.android.Model

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.IdRes

data class ExternalNavigation(@IdRes val screenId: Int, val args: Bundle): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readBundle(Bundle::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(screenId)
        parcel.writeBundle(args)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ExternalNavigation> {
        override fun createFromParcel(parcel: Parcel): ExternalNavigation {
            return ExternalNavigation(parcel)
        }

        override fun newArray(size: Int): Array<ExternalNavigation?> {
            return arrayOfNulls(size)
        }
    }
}