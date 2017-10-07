package com.kpiega.daggerscopping.di

import android.app.Application
import com.kpiega.daggerscopping.ScopeApp
import com.kpiega.daggerscopping.controler.UserManager
import com.kpiega.daggerscopping.di.module.app.activity.ActivityBindingModule
import com.kpiega.daggerscopping.di.module.app.AppModule
import com.kpiega.daggerscopping.di.module.app.InfoModule
import com.kpiega.daggerscopping.di.scope.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication

@AppScope
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        InfoModule::class,
        ActivityBindingModule::class
))
interface AppComponent: AndroidInjector<DaggerApplication> {

    val userManager: UserManager
    val application: Application

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(app: Application): Builder

    }

    fun inject(scopeApp: ScopeApp)

}

