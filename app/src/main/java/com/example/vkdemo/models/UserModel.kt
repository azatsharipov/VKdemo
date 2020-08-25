package com.example.vkdemo.models

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

class UserModel(var firstName: String?, var photo: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<UserModel> {
        override fun createFromParcel(parcel: Parcel): UserModel {
            return UserModel(parcel)
        }

        override fun newArray(size: Int): Array<UserModel?> {
            return arrayOfNulls(size)
        }

        fun parse(json: JSONObject)
                = UserModel(firstName = json.optString("first_name", ""),
//            lastName = json.optString("last_name", ""),
            photo = json.optString("photo_max", ""))
    }
}