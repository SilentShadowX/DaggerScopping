package com.kpiega.daggerscopping.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.kpiega.daggerscopping.R
import com.kpiega.daggerscopping.adapter.OnUserClickListener
import com.kpiega.daggerscopping.adapter.UserListAdapter
import com.kpiega.daggerscopping.base.BaseActivity
import com.kpiega.daggerscopping.base.BaseUserActivity
import com.kpiega.daggerscopping.model.User
import com.kpiega.daggerscopping.ui.presenters.MainPresenter
import com.kpiega.daggerscopping.ui.views.MainView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseUserActivity(), MainView {

    val manager by lazy {
        LinearLayoutManager(this)
    }

    val adapter by lazy {
        UserListAdapter(onUserClickHandler())
    }

    @Inject
    lateinit var presenter: MainPresenter

    private fun onUserClickHandler() = object: OnUserClickListener {
        override fun onUserClick(user: User) {
            showMessage(user.toString())
            presenter.switchToUser(user)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun initView() {
        userList.layoutManager = manager
        userList.adapter = adapter

        MakeExampleCall.setOnClickListener {
            presenter.doExampleNetworkCall()
        }

        logoutButton.setOnClickListener {
            _ -> presenter.logoutUser()
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
        initView()
        presenter.loadCurrentUser()
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }

    override fun updateUser(user: User) {
        LoggedUserValue.text = user.getFullName()
        showMessage(user.toString())
    }

    override fun loadUsers(listUser: MutableList<User>) {
        adapter.userList = listUser
    }

    override fun backToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
