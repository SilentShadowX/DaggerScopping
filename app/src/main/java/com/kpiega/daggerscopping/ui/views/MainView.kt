package com.kpiega.daggerscopping.ui.views

import com.kpiega.daggerscopping.model.User
import com.kpiega.daggerscopping.ui.BaseMvpContract

interface MainView: BaseMvpContract.BaseView {

    fun updateUser(user: User)
    fun loadUsers(listUser: MutableList<User>)
    fun backToLogin()
}