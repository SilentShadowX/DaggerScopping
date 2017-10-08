package com.kpiega.daggerscopping.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.kpiega.daggerscopping.R
import com.kpiega.daggerscopping.base.BaseActivity
import com.kpiega.daggerscopping.ui.presenters.LoginPresenter
import com.kpiega.daggerscopping.ui.views.LoginView
import com.kpiega.sub_activities.module.SubProjectModule
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginView {

    @Inject
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
        initInteraction()
    }

    fun initInteraction() {
        LoginButton.setOnClickListener { _ ->
            presenter.doLogin()
        }

        LoginButton.setOnLongClickListener { _ ->
//            SubProjectModule.redirect?.startSubProjectActivity(this@LoginActivity); true
            true
        }
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }

    override fun showLoading() {
        ProgressLogin.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        ProgressLogin.visibility = View.GONE
    }

    override fun redirectToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun getLogin() = LoginTextField.text.toString()
    override fun getPassword() = PasswordTextField.text.toString()

    override fun lockButton() {
        LoginButton.isEnabled = false
    }

    override fun unlockButton() {
        LoginButton.isEnabled = true
    }


}
