package com.kpiega.daggerscopping.di.module.app.activity

import com.kpiega.daggerscopping.ui.implementation.LoginPresenterImpl
import com.kpiega.daggerscopping.ui.presenters.LoginPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class LoginModule {
    @Binds
    abstract fun provideLoginPresenter(presenter: LoginPresenterImpl): LoginPresenter
}