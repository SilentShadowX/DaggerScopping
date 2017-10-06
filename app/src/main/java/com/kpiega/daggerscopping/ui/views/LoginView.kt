package com.kpiega.daggerscopping.ui.views

import com.kpiega.daggerscopping.ui.BaseMvpContract

interface LoginView: BaseMvpContract.BaseView {

    fun showLoading()
    fun hideLoading()

    fun redirectToMain()

    fun getLogin(): String
    fun getPassword(): String

    fun lockButton()
    fun unlockButton()
}