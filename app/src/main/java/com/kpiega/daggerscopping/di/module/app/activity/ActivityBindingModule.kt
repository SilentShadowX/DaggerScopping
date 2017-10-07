package com.kpiega.daggerscopping.di.module.app.activity

import com.kpiega.daggerscopping.di.module.app.activity.LoginModule
import com.kpiega.daggerscopping.di.scope.ActivityScope
import com.kpiega.daggerscopping.ui.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(
            LoginModule::class)
    )
    abstract fun loginActivity(): LoginActivity
}