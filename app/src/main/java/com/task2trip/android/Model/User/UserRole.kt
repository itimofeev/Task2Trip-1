package com.task2trip.android.Model.User

enum class UserRole {
    TRAVELER,
    LOCAL,
    NOT_AUTHORIZED;

    companion object {
        fun getName(nameParam: String): UserRole {
            var name = nameParam
            return try {
                if (name.equals("traveller", true)) {
                    name = UserRole.TRAVELER.name
                } else if (name.equals("local", true)) {
                    name = UserRole.LOCAL.name
                }
                UserRole.valueOf(name.toUpperCase())
            } catch (ex: Exception) {
                NOT_AUTHORIZED
            }
        }
    }
}