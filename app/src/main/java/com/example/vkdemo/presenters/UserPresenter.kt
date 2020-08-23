package com.example.vkdemo.presenters

import android.widget.ImageView
import com.example.vkdemo.models.UserModel
import com.example.vkdemo.views.UserView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.coroutines.*
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

@InjectViewState
class UserPresenter : MvpPresenter<UserView>() {
    val PROTOCOL = "https://"
    val ADDRESS = PROTOCOL + "api.vk.com/method/"
    fun getUser(userId: String) {
        presenterScope.launch {
            viewState.startRequest()
            val ACCESS_TOKEN = "d6e587ebd6e587ebd6e587eb52d6960e6cdd6e5d6e587eb89d881b3d9cc7dbd0198c341"
            val PHOTO = "fields=photo_max"
            val METHOD = ADDRESS + "users.get?user_id=$userId&$PHOTO&v=5.52&access_token=" + ACCESS_TOKEN
            delay(1000)
            var user: UserModel
            withContext(Dispatchers.IO) { user = getRequest(METHOD) }
            viewState.stopRequest()
            viewState.showUser(user)
        }
    }

    fun getRequest(method: String): UserModel {
        val url = URL(method.toString())
        val conn = url.openConnection() as HttpURLConnection
        var data = "no connection"
        var photo: String? = null
        try {
            // TODO change to Retrofit
            val inputStream = conn.inputStream
            val stream = BufferedInputStream(inputStream)
            val input = readStream(inputStream = stream)
            data = simpleJsonParser(input)
            val jsonData = JSONObject(input)
            photo = jsonData.getJSONArray("response").getJSONObject(0).getString("photo_max")
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            conn.disconnect()
            return UserModel(data, photo)
        }
    }

    fun simpleJsonParser(input: String): String {
        val jsonData = JSONObject(input)
        return jsonData.getJSONArray("response").getJSONObject(0).getString("first_name")
    }

    fun readStream(inputStream: BufferedInputStream): String {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        bufferedReader.forEachLine { stringBuilder.append(it) }
        return stringBuilder.toString()
    }

}