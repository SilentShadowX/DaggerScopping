package com.kpiega.daggerscopping.ui.presenters

import com.kpiega.daggerscopping.ui.BaseMvpContract
import com.kpiega.daggerscopping.ui.views.LoginView

interface LoginPresenter: BaseMvpContract.BasePresener<LoginView> {
    fun doLogin()
}