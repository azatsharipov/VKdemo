package com.example.vkdemo.models

import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject

class VKApiRequest: VKRequest<UserModel> {
    constructor(id: String): super("users.get") {
        addParam("user_id", id)
        addParam("fields", "photo_max")
    }

    override fun parse(r: JSONObject): UserModel {
        val users = r.getJSONArray("response")
        val result = UserModel.parse(users.getJSONObject(0))
        return result
    }
}