package com.example.vkdemo.presenters

import android.content.Intent
import com.example.vkdemo.R
import com.example.vkdemo.views.LoginView
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKTokenExpiredHandler
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {

    fun login(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        val callback = object: VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
//                VK.addTokenExpiredHandler(tokenTracker)
                viewState.saveUserId(token.userId.toString())
                viewState.openMenu()
            }

            override fun onLoginFailed(errorCode: Int) {
                viewState.showError(R.string.error_user_did_not_passed)
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
//            super.onActivityResult(requestCode, resultCode, data)
            viewState.showError(R.string.error_data_null)
            return false
        }
        return true
    }
}