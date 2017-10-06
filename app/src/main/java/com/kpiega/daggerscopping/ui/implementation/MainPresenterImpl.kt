package com.kpiega.daggerscopping.ui.implementation

import com.kpiega.daggerscopping.controler.UserManager
import com.kpiega.daggerscopping.model.User
import com.kpiega.daggerscopping.ui.presenters.MainPresenter
import com.kpiega.daggerscopping.ui.views.MainView
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(
        val userManager: UserManager
) : MainPresenter {

    override var view: MainView? = null

    override fun attachView(view: MainView) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun switchToUser(user: User) {
        userManager.switchUser(user)
        view?.updateUser(user)
    }

    override fun loadCurrentUser() {
        view?.loadUsers(userManager.listUser)
        view?.updateUser(userManager.getCurrentLoggedUser()!!)
    }

    override fun doExampleNetworkCall() {
        
    }

    override fun logoutUser() {
        userManager.eraseUserSession()
        view?.backToLogin()
    }
}