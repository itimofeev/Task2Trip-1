package com.task2trip.android.Model.Location

import android.os.Parcel
import android.os.Parcelable

data class LatLng(val lat: Double, val long: Double, var radius: Int = 1000): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(lat)
        parcel.writeDouble(long)
        parcel.writeInt(radius)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LatLng> {
        override fun createFromParcel(parcel: Parcel): LatLng {
            return LatLng(parcel)
        }

        override fun newArray(size: Int): Array<LatLng?> {
            return arrayOfNulls(size)
        }
    }

    fun isEmpty(): Boolean {
        return lat == 0.0 && long == 0.0
    }
}