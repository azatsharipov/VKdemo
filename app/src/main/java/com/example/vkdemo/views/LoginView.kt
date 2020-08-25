package com.example.vkdemo.views

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd

@AddToEnd
interface LoginView : MvpView {
    fun startRequest()
    fun stopRequest()
    fun showError(resourceId: Int)
    fun openMenu()
}