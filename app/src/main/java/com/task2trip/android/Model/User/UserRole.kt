package com.task2trip.android.Model.User

enum class UserRole {
    TRAVELER,
    PERFORMER,
    NOT_AUTHORIZED;

    companion object {
        fun getName(nameParam: String): UserRole {
            var name = nameParam
            return try {
                if (name.equals("traveller", true)) {
                    name = UserRole.TRAVELER.name
                }
                UserRole.valueOf(name.toUpperCase())
            } catch (ex: Exception) {
                NOT_AUTHORIZED
            }
        }
    }
}