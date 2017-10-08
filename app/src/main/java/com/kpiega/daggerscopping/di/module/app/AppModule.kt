package com.kpiega.daggerscopping.di.module.app

import com.kpiega.daggerscopping.di.scope.AppScope
import com.kpiega.sub_activities.manager.ModuleManager
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideModuleManager() = ModuleManager()
}


