package com.kpiega.daggerscopping.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.kpiega.daggerscopping.ui.BaseMvpContract
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity(), HasSupportFragmentInjector, BaseMvpContract.BaseView {

    @Inject lateinit var injector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        androidInject()
        super.onCreate(savedInstanceState)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return injector
    }

    protected open fun androidInject() {
        AndroidInjection.inject(this)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}