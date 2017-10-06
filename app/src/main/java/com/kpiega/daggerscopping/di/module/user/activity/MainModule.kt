package com.kpiega.daggerscopping.di.module.user.activity

import com.kpiega.daggerscopping.ui.implementation.MainPresenterImpl
import com.kpiega.daggerscopping.ui.presenters.MainPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class MainModule {
    @Binds
    abstract fun provideMainPresenter(presenter: MainPresenterImpl): MainPresenter
}