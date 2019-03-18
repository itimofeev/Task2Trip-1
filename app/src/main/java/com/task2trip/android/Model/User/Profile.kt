package com.task2trip.android.Model.User

import com.task2trip.android.Model.Location.GeoCountryCity
import com.task2trip.android.Model.Task.TaskCategory

interface Profile {
    fun getFirstName(): String
    fun getLastName(): String
    fun getMiddleName(): String
    fun getImageAvatarUrl(): String
    fun getSex(): String
    fun getBirthDate(): String
    fun getFieldOfActivity(): String
    fun getInterests(): String
    fun getAbout(): String
    fun getWhyUse(): String
    fun getCategories(): List<TaskCategory>
    fun getLocation(): GeoCountryCity

    fun setFirstName(firstName: String)
    fun setLastName(lastName: String)
    fun setMiddleName(middleName: String)
    fun setImageAvatarUrl(imageUrl: String)
    fun setSex(sex: String)
    fun setBirthDate(birthDate: String)
    fun setFieldOfActivity(fieldOfActivity: String)
    fun setInterests(interests: String)
    fun setAbout(about: String)
    fun setWhyUse(whyUse: String)
    fun setCategories(items: List<TaskCategory>)
    fun setLocation(location: GeoCountryCity)
}