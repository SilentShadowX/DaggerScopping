package com.kpiega.daggerscopping.di

import android.app.Activity
import dagger.android.HasActivityInjector

class UserInjection {

    companion object {
        fun inject(activity: Activity, injector: HasActivityInjector) {
            injector.activityInjector().inject(activity)
        }
    }

}