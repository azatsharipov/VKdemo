package com.example.vkdemo.models

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

class ChatModel(id: String?, firstName: String?, lastName: String?, var lastMessage: String?, photo: String?) :
    UserModel(id, firstName, lastName, photo),
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
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

    companion object CREATOR : Parcelable.Creator<ChatModel> {
        override fun createFromParcel(parcel: Parcel): ChatModel {
            return ChatModel(parcel)
        }

        override fun newArray(size: Int): Array<ChatModel?> {
            return arrayOfNulls(size)
        }

        fun parse(json: JSONObject) = ChatModel(
            id = json.optString("id", ""),
            firstName = json.optString("first_name", ""),
            lastName = json.optString("last_name", ""),
            lastMessage = json.optString("last_message", ""),
            photo = json.optString("photo_100", ""))

        fun parse(lastMessage: String, user: UserModel) = ChatModel(
            id = user.id,
            firstName = user.firstName,
            lastName = user.lastName,
            lastMessage = lastMessage,
            photo = user.photo
        )
    }
}