package com.example.vkdemo.models

import com.vk.api.sdk.*
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

class ApiMail: VKRequest<ArrayList<ChatModel>> {
    constructor() : super("messages.getConversations") {
//        addParam("user_id", id)
        addParam("offset", "0")
        addParam("count", "20")
        addParam("filter", "all")
    }

    override fun parse(r: JSONObject): ArrayList<ChatModel> {
        val chats = r.getJSONObject("response").getJSONArray("items")
        val resultChats = ArrayList<ChatModel>()
        for (i in 0 until chats.length()) {
            val id = chats.getJSONObject(i).getJSONObject("conversation").getJSONObject("peer").optString("id", "")
            val lastMessage = chats.getJSONObject(i).getJSONObject("last_message").optString("text", "")
            VK.execute(ApiUsers(id),  object: VKApiCallback<UserModel> {
                override fun success(result: UserModel) {
                    resultChats.add(ChatModel.parse(lastMessage, result))
                }
                override fun fail(error: Exception) {
                }
            })
        }
        return resultChats
    }
}

class ApiFriends: VKRequest<ArrayList<UserModel>> {
    constructor(): super("friends.get") {
//        addParam("user_id", id)
        addParam("order", "name")
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