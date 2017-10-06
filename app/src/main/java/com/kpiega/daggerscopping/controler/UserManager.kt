package com.kpiega.daggerscopping.controler

import android.app.Activity
import android.app.Application
import com.kpiega.daggerscopping.ScopeApp
import com.kpiega.daggerscopping.di.AppComponent
import com.kpiega.daggerscopping.di.DaggerUserComponent
import com.kpiega.daggerscopping.di.UserComponent
import com.kpiega.daggerscopping.di.scope.AppScope
import com.kpiega.daggerscopping.model.User
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

@AppScope
class UserManager @Inject constructor() : HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    var userComponent: UserComponent? = null
        private set

    var user: User? = null

    val listUser = mutableListOf<User>()

    fun createUserSession(user: User) {

        if (userComponent != null)
            return

        userComponent = DaggerUserComponent.builder()
                .appComponent(ScopeApp.app.appComponent)
                .setUser(user)
                .build()

        userComponent?.inject(this)

        this.user = user
        if (listUser.all { it.login != user.login } )
            this.listUser.add(user)
    }

    fun eraseUserSession() {
        userComponent = null
    }

    fun eraseUser() {
        this.listUser.remove(getCurrentLoggedUser())
    }

    fun switchUser(user: User) {
        if (userComponent != null)
            eraseUserSession()

        createUserSession(user)
    }

    fun getCurrentLoggedUser() = user

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}