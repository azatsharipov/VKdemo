package com.example.vkdemo.presenters

import com.example.vkdemo.R
import com.example.vkdemo.models.ApiFriends
import com.example.vkdemo.models.ApiUsers
import com.example.vkdemo.models.UserModel
import com.example.vkdemo.views.MailView
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MailPresenter : MvpPresenter<MailView>() {

    fun getFriends() {
        viewState.startRequest()
        VK.execute(ApiFriends(),  object: VKApiCallback<ArrayList<UserModel>> {
            override fun success(result: ArrayList<UserModel>) {
                viewState.stopRequest()
                viewState.showFriends(result)
            }
            override fun fail(error: Exception) {
                viewState.stopRequest()
                viewState.showError(R.string.error_failed_request)
            }
        })
    }

}