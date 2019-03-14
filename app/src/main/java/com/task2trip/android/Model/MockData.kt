package com.task2trip.android.Model

import com.task2trip.android.Model.Task.Task
import com.task2trip.android.Model.Task.TaskAddCategory
import com.task2trip.android.Model.Task.TaskCategory
import com.task2trip.android.Model.User.UserImpl
import com.task2trip.android.Model.User.UserInfoResp
import com.task2trip.android.Model.User.UserLoginResp
import com.task2trip.android.Model.User.UserSignUpResp

class MockData {
    companion object {
        fun dataTaskAddCategory(): List<TaskAddCategory> {
            val items = ArrayList<TaskAddCategory>()
            items.add(TaskAddCategory(1, "", "Category 001", "Category Description 001"))
            items.add(TaskAddCategory(2, "", "Маршруты путешествия", "Category Description 002"))
            items.add(TaskAddCategory(3, "", "Category 003", "Category Description 003"))
            items.add(TaskAddCategory(4, "", "Category 004", "Category Description 004"))
            items.add(TaskAddCategory(5, "", "Category 005", "Category Description 005"))
            items.add(TaskAddCategory(6, "", "Category 006", "Category Description 006"))
            return items
        }

        fun getEmptyTask(): Task {
            return Task("", "", "", "",
                "", "", 0, "",
                "", "", "", "", getEmptyCategory(), getEmptyUser(), "")
        }

        fun getEmptyCategory(): TaskCategory {
            return TaskCategory("", "", "", "", false)
        }

        fun getEmptyUser(): UserImpl {
            return UserImpl()
        }

        fun getEmptyOffer(): Offer {
            return Offer("", "", 0, getEmptyUser(), "", "", getEmptyTask())
        }

        fun getEmptyOfferList(): List<Offer> {
            return ArrayList<Offer>()
        }

        fun getOfferList(): List<Offer> {
            val offers = ArrayList<Offer>()
            offers.add(Offer("1", "test comment 11", 111, getEmptyUser(), "", "", getEmptyTask()))
            offers.add(Offer("2", "test comment 22", 222, getEmptyUser(), "", "", getEmptyTask()))
            offers.add(Offer("3", "test comment 33", 333, getEmptyUser(), "", "", getEmptyTask()))
            offers.add(Offer("4", "test comment 44", 444, getEmptyUser(), "", "", getEmptyTask()))
            offers.add(Offer("5", "test comment 55", 555, getEmptyUser(), "", "", getEmptyTask()))
            return offers
        }

        fun getEmptyUserInfoResp(): UserInfoResp {
            return UserInfoResp("", "", "", "")
        }

        fun getEmptyUserSignUpResp(): UserSignUpResp {
            return UserSignUpResp("", "")
        }

        fun getEmptyUserLoginResp(): UserLoginResp {
            return UserLoginResp("", getEmptyUser())
        }

        fun getGeoLocations(): ArrayList<GeoCountryCity> {
            val items = ArrayList<GeoCountryCity>()
            items.add(GeoCountryCity("Россия", "Москва", LatLng(55.755814, 37.617635)))
            items.add(GeoCountryCity("Пембрукшир-Сэр-Бенфроу, Великобритания", "Ллис-и-фран", LatLng(51.884496, -4.844983)))
            items.add(GeoCountryCity("Кувен, Намюр, Бельгия", "Фран", LatLng(50.081247, 4.514978)))
            items.add(GeoCountryCity("Овернь-Рона-Альпы, Франция", "Фран", LatLng(45.992277, 4.779154)))
            items.add(GeoCountryCity("Нагайбакский район, Челябинская область, Россия", "село Париж", LatLng(53.296639, 60.10156)))
            items.add(GeoCountryCity("Иль-де-Франс, Франция", "Париж", LatLng(48.856663, 2.351556)))
            items.add(GeoCountryCity("Онтарио, Канада", "Париж", LatLng(43.199399, -80.385186)))
            return items
        }

        fun getEmptyGeoLocationsList(): ArrayList<GeoCountryCity> {
            return ArrayList<GeoCountryCity>()
        }

        fun getEmptyGeoLocations(): GeoCountryCity {
            return GeoCountryCity("", "", LatLng(0.0, 0.0))
        }
    }
}