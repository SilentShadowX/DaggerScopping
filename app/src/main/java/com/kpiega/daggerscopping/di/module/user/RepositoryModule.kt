package com.kpiega.daggerscopping.di.module.user

import com.kpiega.daggerscopping.api.TestServiceRepository
import com.kpiega.daggerscopping.api.TestServiceRepositoryImpl
import com.kpiega.daggerscopping.di.scope.ActivityScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideTestRepository(repository: TestServiceRepositoryImpl): TestServiceRepository
}