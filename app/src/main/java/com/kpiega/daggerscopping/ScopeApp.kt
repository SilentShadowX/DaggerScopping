package com.kpiega.daggerscopping

import android.app.Activity
import android.app.Application
import com.kpiega.daggerscopping.di.AppComponent
import com.kpiega.daggerscopping.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class ScopeApp : Application(), HasActivityInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        app = this
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()

        appComponent.inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    companion object {
        lateinit var app: ScopeApp
            private set
    }

}