package com.kpiega.daggerscopping

import android.app.Activity
import android.app.Application
import com.kpiega.daggerscopping.api.TestServiceRepository
import com.kpiega.daggerscopping.di.AppComponent
import com.kpiega.daggerscopping.di.DaggerAppComponent
import com.kpiega.daggerscopping.model.User
import com.kpiega.sub_activities.manager.ModuleManager
import com.kpiega.sub_interface.interfaces.ModulePreference
import com.kpiega.sub_interface.interfaces.ModuleRequstsNetwork
import com.kpiega.sub_activities.manager.utils.HasSubModule
import dagger.Lazy
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Provider

class ScopeApp : Application(), HasActivityInjector, HasSubModule {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject lateinit var module: Lazy<ModuleManager>

    lateinit var appComponent: AppComponent
        private set

    var testServiceRepo = object: TestServiceRepository {
        override fun makeTestRequest(user: User): Observable<String> {
            return Observable.just("makeTestRequest")
        }

        override fun makeDataRequest(data: String): Observable<String> {
            return Observable.just("makeTestRequest")
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this

        initAppComponent()

        if(enableSubProject) {
            initModule()
        }
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()

        appComponent.inject(this)
    }

    private fun initModule() {
        val manager = module.get()
        manager.createSession(
                appComponent,
                provideRequestNetwork(),
                providePreference()
        )
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

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun hasModule(): Boolean {
        return enableSubProject
    }

    override fun moduleManager(): ModuleManager? {
        return module.get()
    }

    companion object {
        val enableSubProject = true
        lateinit var app: ScopeApp
            private set
    }

}


