package com.kpiega.daggerscopping

import android.app.Activity
import android.app.Application
import com.kpiega.daggerscopping.api.TestServiceRepository
import com.kpiega.daggerscopping.di.AppComponent
import com.kpiega.daggerscopping.di.DaggerAppComponent
import com.kpiega.sub_activities.di.DaggerSecondModuleComponent
import com.kpiega.sub_activities.di.SecondModuleComponent
import com.kpiega.sub_interface.interfaces.ModulePreference
import com.kpiega.sub_interface.interfaces.ModuleRequstsNetwork
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.Observable
import javax.inject.Inject

class ScopeApp : Application(), HasActivityInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    lateinit var appComponent: AppComponent
        private set

    lateinit var moduleComponent: SecondModuleComponent

    lateinit var testServiceRepo: TestServiceRepository

    override fun onCreate() {
        super.onCreate()
        app = this

        initAppComponent()

        if(enableSubProject) {
            initModule()
        }
    }

    fun initModule() {

        moduleComponent = DaggerSecondModuleComponent.builder()
                .appComponent(appComponent)
                .buildNetworkRequest(provideRequestNetwork())
                .buildPreference(providePreference())
                .build()

    }

    private fun providePreference() = object: ModulePreference {
        override fun isAttachToCore() = true
        override fun moduleWelcomeText() = "Hello my friend! Core here!"
    }


    private fun provideRequestNetwork() = object: ModuleRequstsNetwork {
        override fun dowloadModuleData(data: String): Observable<String> {
            return testServiceRepo.makeDataRequest(data)
        }
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()

        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    companion object {
        val enableSubProject = true
        lateinit var app: ScopeApp
            private set
    }



}


