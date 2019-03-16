package com.task2trip.android.Model.User

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import com.task2trip.android.Common.Constants
import com.task2trip.android.Model.LocalStoreManager
import com.task2trip.android.Model.MockData

class UserImpl() : User, Parcelable {
    private var id: String = ""
    private var role: String = ""
    private var token: String = ""
    private var profile: ProfileImpl = MockData.getEmptyProfile()

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        role = parcel.readString()
        token = parcel.readString()
        profile = parcel.readParcelable(ProfileImpl::class.java.classLoader)
    }

    override fun isAuthorized(): Boolean {
        return token.isNotEmpty()
    }

    override fun getId(): String {
        return id
    }

    override fun getRole(): UserRole {
        return UserRole.getName(role)
    }

    override fun getToken(): String {
        return token
    }

    override fun getProfile(): Profile {
        return profile
    }

    override fun getName(): String {
        return getProfile().getFirstName().plus(" ").plus(getProfile().getLastName()).trim()
    }

    override fun setRole(role: UserRole) {
        this.role = role.name
    }

    override fun setToken(token: String) {
        this.token = token
    }

    override fun setProfile(profile: ProfileImpl) {
        this.profile = profile
    }

    override fun saveUserData(context: Context) {
        userData(true, context)
    }

    override fun deleteUserData(context: Context) {
        userData(false, context)
    }

    private fun initStorage(context: Context): LocalStoreManager {
        return LocalStoreManager(context)
    }

    private fun userData(isSave: Boolean, context: Context) {
        val storage = initStorage(context)
        if (isSave) {
            with(storage) {
                set(Constants.EXTRA_USER_ID, id)
                set(Constants.EXTRA_USER_ROLE, role)
                set(Constants.EXTRA_USER_TOKEN, token)
                set(Constants.EXTRA_USER_FIRST_NAME, profile.getFirstName())
                set(Constants.EXTRA_USER_LAST_NAME, profile.getLastName())
                set(Constants.EXTRA_USER_MIDDLE_NAME, profile.getMiddleName())
                set(Constants.EXTRA_USER_IMAGE_URL, profile.getImageAvatarUrl())
                set(Constants.EXTRA_USER_SEX, profile.getSex())
                set(Constants.EXTRA_USER_BIRTHDATE, profile.getBirthDate())
                set(Constants.EXTRA_USER_FIELD_OF_ACTIVITY, profile.getFieldOfActivity())
                set(Constants.EXTRA_USER_INTEREST, profile.getInterests())
                set(Constants.EXTRA_USER_ABOUT, profile.getAbout())
                set(Constants.EXTRA_USER_WHY_USE, profile.getWhyUse())
            }
        } else {
            with(storage) {
                set(Constants.EXTRA_USER_ID, "")
                set(Constants.EXTRA_USER_ROLE, UserRole.NOT_AUTHORIZED.name)
                set(Constants.EXTRA_USER_TOKEN, "")
                set(Constants.EXTRA_USER_FIRST_NAME, "")
                set(Constants.EXTRA_USER_LAST_NAME, "")
                set(Constants.EXTRA_USER_MIDDLE_NAME, "")
                set(Constants.EXTRA_USER_IMAGE_URL, "")
                set(Constants.EXTRA_USER_SEX, "")
                set(Constants.EXTRA_USER_BIRTHDATE, "")
                set(Constants.EXTRA_USER_FIELD_OF_ACTIVITY, "")
                set(Constants.EXTRA_USER_INTEREST, "")
                set(Constants.EXTRA_USER_ABOUT, "")
                set(Constants.EXTRA_USER_WHY_USE, "")
            }
        }
    }

    override fun initStorageData(context: Context) {
        val storage = initStorage(context)
        with(storage) {
            id = get(Constants.EXTRA_USER_ID, "")!!
            role = get(Constants.EXTRA_USER_ROLE, UserRole.NOT_AUTHORIZED.name)!!
            token = get(Constants.EXTRA_USER_TOKEN, "")!!
            profile.setFirstName(get(Constants.EXTRA_USER_FIRST_NAME, "")!!)
            profile.setLastName(get(Constants.EXTRA_USER_LAST_NAME, "")!!)
            profile.setMiddleName(get(Constants.EXTRA_USER_MIDDLE_NAME, "")!!)
            profile.setImageAvatarUrl(get(Constants.EXTRA_USER_IMAGE_URL, "")!!)
            profile.setSex(UserSex.getUserSex(get(Constants.EXTRA_USER_SEX, false)!!).name)
            profile.setBirthDate(get(Constants.EXTRA_USER_BIRTHDATE, "")!!)
            profile.setFieldOfActivity(get(Constants.EXTRA_USER_FIELD_OF_ACTIVITY, "")!!)
            profile.setInterests(get(Constants.EXTRA_USER_INTEREST, "")!!)
            profile.setAbout(get(Constants.EXTRA_USER_ABOUT, "")!!)
            profile.setWhyUse(get(Constants.EXTRA_USER_WHY_USE, "")!!)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(role)
        parcel.writeString(token)
        parcel.writeParcelable(profile, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserImpl> {
        override fun createFromParcel(parcel: Parcel): UserImpl {
            return UserImpl(parcel)
        }

        override fun newArray(size: Int): Array<UserImpl?> {
            return arrayOfNulls(size)
        }
    }
}