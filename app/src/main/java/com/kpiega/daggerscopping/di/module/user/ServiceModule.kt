package com.kpiega.daggerscopping.di.module.user

import com.kpiega.daggerscopping.api.TestService
import com.kpiega.daggerscopping.di.scope.AppScope
import com.kpiega.daggerscopping.di.scope.UserScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ServiceModule {

    @Provides
    @UserScope
    fun provideTestService(retrofit: Retrofit) =
            retrofit.create(TestService::class.java)

}