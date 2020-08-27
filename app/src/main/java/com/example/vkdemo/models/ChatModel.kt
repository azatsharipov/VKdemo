package com.example.vkdemo.models

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

class ChatModel(var firstName: String?, var lastMessage: String?, var photo: String?) : Parcelable{
    constructor(parcel: Parcel) : this(
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

        fun parse(json: JSONObject)
                = ChatModel(firstName = json.optString("first_name", ""),
            lastMessage = json.optString("last_message", ""),
            photo = json.optString("photo_100", ""))
    }
}