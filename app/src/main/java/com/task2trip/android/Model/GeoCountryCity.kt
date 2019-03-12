package com.task2trip.android.Model

import android.os.Parcel
import android.os.Parcelable

data class GeoCountryCity(val description: String, val name: String, val point: LatLng): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(LatLng::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeString(name)
        parcel.writeParcelable(point, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GeoCountryCity> {
        override fun createFromParcel(parcel: Parcel): GeoCountryCity {
            return GeoCountryCity(parcel)
        }

        override fun newArray(size: Int): Array<GeoCountryCity?> {
            return arrayOfNulls(size)
        }
    }
}