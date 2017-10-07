package com.kpiega.daggerscopping.ui.implementation

import com.kpiega.daggerscopping.api.TestServiceRepository
import com.kpiega.daggerscopping.controler.UserManager
import com.kpiega.daggerscopping.model.User
import com.kpiega.daggerscopping.ui.presenters.MainPresenter
import com.kpiega.daggerscopping.ui.views.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(
        val userManager: UserManager,
        val repository: TestServiceRepository
) : MainPresenter {

    override var view: MainView? = null
    val disposable = CompositeDisposable()

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
        disposable.add(
                repository.makeTestRequest(userManager.getCurrentLoggedUser() as User)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeBy(
                                {
                                    view?.showMessage(it)
                                },
                                {
                                    view?.showMessage(it.message ?: "Nieznany błąd")
                                },
                                {
                                    print("Zakończono")
                                }
                        )
        )
    }

    override fun logoutUser() {
        userManager.eraseUserSession()
        view?.backToLogin()
    }
}