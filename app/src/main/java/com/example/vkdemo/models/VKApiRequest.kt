package com.example.vkdemo.models

import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class ApiUsers: VKRequest<UserModel> {
    constructor(id: String): super("users.get") {
        addParam("user_id", id)
        addParam("fields", "photo_100")
    }

    override fun parse(r: JSONObject): UserModel {
        val users = r.getJSONArray("response")
        val result = UserModel.parse(users.getJSONObject(0))
        return result
    }
}

class ApiFriends: VKRequest<ArrayList<UserModel>> {
    constructor(): super("friends.get") {
//        addParam("user_id", id)
        addParam("fields", "photo_100")
    }

    override fun parse(r: JSONObject): ArrayList<UserModel> {
        val users = r.getJSONObject("response").getJSONArray("items")
        val result = ArrayList<UserModel>()
        for (i in 0 until users.length()) {
            result.add(UserModel.parse(users.getJSONObject(i)))
        }
        return result
    }
}