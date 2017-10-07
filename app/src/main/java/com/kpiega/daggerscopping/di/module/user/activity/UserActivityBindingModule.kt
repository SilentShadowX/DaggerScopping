package com.kpiega.daggerscopping.di.module.user.activity

import com.kpiega.daggerscopping.di.module.user.RepositoryModule
import com.kpiega.daggerscopping.di.scope.ActivityScope
import com.kpiega.daggerscopping.di.scope.UserScope
import com.kpiega.daggerscopping.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(
            MainModule::class, RepositoryModule::class
    ))
    abstract fun mainActivity(): MainActivity
}