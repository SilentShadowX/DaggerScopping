package com.kpiega.daggerscopping.ui.implementation

import com.kpiega.daggerscopping.controler.UserManager
import com.kpiega.daggerscopping.model.User
import com.kpiega.daggerscopping.ui.presenters.LoginPresenter
import com.kpiega.daggerscopping.ui.views.LoginView
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoginPresenterImpl @Inject constructor(
        val userManager: UserManager
) : LoginPresenter {
    override var view: LoginView? = null

    override fun attachView(view: LoginView) {
        this.view = view

        if(userManager.listUser.size < 1)
            userManager.listUser.addAll(
                    mutableListOf(
                            User(
                                    name = "Kamil", lastname = "P",
                                    login = "Kamil", password = "P",
                                    token = UUID.randomUUID().toString()
                            ),
                            User(
                                    name = "Łukasz", lastname = "F",
                                    login = "Łukasz", password = "F",
                                    token = UUID.randomUUID().toString()
                            ),
                            User(
                                    name = "Artur", lastname = "D",
                                    login = "Artur", password = "D",
                                    token = UUID.randomUUID().toString()
                            )
                    )
            )
    }

    override fun detachView() {
        this.view = null
    }

    override fun doLogin() {

        view?.showLoading()
        view?.lockButton()

        val login = view?.getLogin() ?: ""
        val password = view?.getPassword() ?: ""

        Observable.timer(3000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { doLoginDelay(login, password) }
                .subscribe()

    }

    fun doLoginDelay(login: String, password: String) {
        if(login != "" && password != "") {
            userManager.createUserSession(User(
                    name = login, lastname = password,
                    login = login, password = password,
                    token = UUID.randomUUID().toString()
            ))
            view?.unlockButton()
            view?.hideLoading()
            view?.redirectToMain()

        } else {
            view?.showMessage("Błędny login lub hasło")
            view?.hideLoading()
            view?.unlockButton()
        }
    }


}