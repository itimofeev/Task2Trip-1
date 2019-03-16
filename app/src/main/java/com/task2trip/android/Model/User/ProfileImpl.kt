package com.task2trip.android.Model.User

import android.os.Parcel
import android.os.Parcelable
import com.task2trip.android.Model.Task.TaskCategory

data class ProfileImpl(private var firstName: String,
                       private var lastName: String,
                       private var middleName: String,
                       private var imageUrl: String,
                       private var sex: String,
                       private var birthDate: String,
                       private var fieldOfActivity: String,
                       private var interests: String,
                       private var about: String,
                       private var whyUse: String,
                       private var categories: List<TaskCategory>): Profile, Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(TaskCategory)
    )

    override fun getFirstName(): String {
        return firstName
    }

    override fun getLastName(): String {
        return lastName
    }

    override fun getMiddleName(): String {
        return middleName
    }

    override fun getImageAvatarUrl(): String {
        return imageUrl
    }

    override fun getSex(): Boolean {
        return sex == "male"
    }

    override fun getBirthDate(): String {
        return birthDate
    }

    override fun getFieldOfActivity(): String {
        return fieldOfActivity
    }

    override fun getInterests(): String {
        return interests
    }

    override fun getAbout(): String {
        return about
    }

    override fun getWhyUse(): String {
        return whyUse
    }

    override fun getCategories(): List<TaskCategory> {
        return categories
    }

    override fun setFirstName(firstName: String) {
        this.firstName = firstName
    }

    override fun setLastName(lastName: String) {
        this.lastName = lastName
    }

    override fun setMiddleName(middleName: String) {
        this.middleName = middleName
    }

    override fun setImageAvatarUrl(imageUrl: String) {
        this.imageUrl = imageUrl
    }

    override fun setSex(sex: String) {
        this.sex = sex
    }

    override fun setBirthDate(birthDate: String) {
        this.birthDate = birthDate
    }

    override fun setFieldOfActivity(fieldOfActivity: String) {
        this.fieldOfActivity = fieldOfActivity
    }

    override fun setInterests(interests: String) {
        this.interests = interests
    }

    override fun setAbout(about: String) {
        this.about = about
    }

    override fun setWhyUse(whyUse: String) {
        this.whyUse = whyUse
    }

    override fun setCategories(items: List<TaskCategory>) {
        this.categories = items
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(middleName)
        parcel.writeString(imageUrl)
        parcel.writeString(sex)
        parcel.writeString(birthDate)
        parcel.writeString(fieldOfActivity)
        parcel.writeString(interests)
        parcel.writeString(about)
        parcel.writeString(whyUse)
        parcel.writeTypedList(categories)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProfileImpl> {
        override fun createFromParcel(parcel: Parcel): ProfileImpl {
            return ProfileImpl(parcel)
        }

        override fun newArray(size: Int): Array<ProfileImpl?> {
            return arrayOfNulls(size)
        }
    }
}