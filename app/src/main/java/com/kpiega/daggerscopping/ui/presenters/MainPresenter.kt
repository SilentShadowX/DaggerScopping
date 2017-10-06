package com.kpiega.daggerscopping.ui.presenters

import com.kpiega.daggerscopping.model.User
import com.kpiega.daggerscopping.ui.BaseMvpContract
import com.kpiega.daggerscopping.ui.views.MainView

interface MainPresenter: BaseMvpContract.BasePresener<MainView> {
    fun switchToUser(user: User)
    fun loadCurrentUser()
    fun doExampleNetworkCall()
    fun logoutUser()
}