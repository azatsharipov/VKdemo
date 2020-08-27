package com.example.vkdemo.views

import com.example.vkdemo.models.UserModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEnd
interface FriendsView : MvpView {
    fun startRequest()
    fun stopRequest()
    fun showError(resourceId: Int)
    fun showFriends(friends: ArrayList<UserModel>)
}