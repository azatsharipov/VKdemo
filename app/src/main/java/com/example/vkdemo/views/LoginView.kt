package com.example.vkdemo.views

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEnd
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface LoginView : MvpView {
    fun startRequest()
    fun stopRequest()
    fun showError(resourceId: Int)
    @Skip
    fun openMenu()
}