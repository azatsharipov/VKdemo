package com.example.vkdemo.views

import android.widget.ImageView
import com.example.vkdemo.models.UserModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd

@AddToEnd
interface UserView : MvpView {
    fun startRequest()
    fun stopRequest()
    fun showError(resourceId: Int)
    fun showUser(name: UserModel)
}