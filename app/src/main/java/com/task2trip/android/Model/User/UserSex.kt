package com.task2trip.android.Model.User

enum class UserSex {
    male,
    female;

    companion object {
        fun getUserSex(sex: Boolean): UserSex {
            return if (sex) {
                male
            } else {
                female
            }
        }
    }
}