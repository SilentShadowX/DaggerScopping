package com.kpiega.daggerscopping.di.module.user.activity

import com.kpiega.daggerscopping.di.scope.UserScope
import com.kpiega.daggerscopping.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserActivityBindingModule {

    @ContributesAndroidInjector(modules = arrayOf(
            MainModule::class
    ))
    abstract fun mainActivity(): MainActivity
}