package com.kpiega.daggerscopping.di.module.app

import com.kpiega.daggerscopping.api.SERVER_ADDRESS

import com.kpiega.daggerscopping.di.module.qualifier.ServerInfo
import com.kpiega.daggerscopping.di.scope.AppScope
import dagger.Module
import dagger.Provides
@Module
class InfoModule {

    @Provides
    @AppScope
    @ServerInfo
    fun provideHttpAddress() = SERVER_ADDRESS

}
