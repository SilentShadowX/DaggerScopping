package com.kpiega.daggerscopping.di

import com.kpiega.daggerscopping.controler.UserManager
import com.kpiega.daggerscopping.di.module.UserModule
import com.kpiega.daggerscopping.di.module.user.NetworkModule
import com.kpiega.daggerscopping.di.module.user.ServiceModule
import com.kpiega.daggerscopping.di.module.user.activity.UserActivityBindingModule
import com.kpiega.daggerscopping.di.scope.UserScope
import com.kpiega.daggerscopping.model.User
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication

@UserScope
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        UserActivityBindingModule::class,
        UserModule::class,
        NetworkModule::class,
        ServiceModule::class
), dependencies = arrayOf(
        AppComponent::class
))
interface UserComponent: AndroidInjector<DaggerApplication> {

    fun inject(manager: UserManager)

    @Component.Builder
    interface Builder {

        fun build(): UserComponent

        @BindsInstance
        fun setUser(user: User): Builder

        fun appComponent(appComponent: AppComponent): Builder

    }

}

