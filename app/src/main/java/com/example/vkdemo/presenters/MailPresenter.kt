package com.example.vkdemo.presenters

import com.example.vkdemo.R
import com.example.vkdemo.models.*
import com.example.vkdemo.views.MailView
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MailPresenter : MvpPresenter<MailView>() {

    fun getFriends() {
        viewState.startRequest()
        VK.execute(ApiMail(),  object: VKApiCallback<ArrayList<ChatModel>> {
            override fun success(result: ArrayList<ChatModel>) {
                viewState.stopRequest()
                viewState.showFriends(result)
            }
            override fun fail(error: Exception) {
                viewState.stopRequest()
                viewState.showError(R.string.error_access_messages)
            }
        })
    }

}