package com.kpiega.daggerscopping.base

import android.os.Bundle
import com.kpiega.daggerscopping.ScopeApp
import com.kpiega.daggerscopping.controler.UserManager
import com.kpiega.daggerscopping.di.UserInjection

abstract class BaseUserActivity : BaseActivity(){

    lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun androidInject() {
        userManager = (application as ScopeApp)
                .appComponent.userManager
        UserInjection.inject(this, userManager)
    }


}