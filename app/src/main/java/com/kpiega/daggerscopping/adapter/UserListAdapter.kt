package com.kpiega.daggerscopping.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kpiega.daggerscopping.R
import com.kpiega.daggerscopping.model.User
import kotlin.properties.Delegates

class UserListAdapter(val listener: OnUserClickListener) : RecyclerView.Adapter<UserViewHolder>(), AutoUpdatableAdapter {

    var userList by Delegates.observable(mutableListOf<User>()) { _, old, new ->
        autoNotify(old, new) { o, n ->
            o.login == n.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.row_user_layout, parent, false)
        return UserViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: UserViewHolder?, position: Int) {
        holder?.bind(userList[position])
    }

    override fun getItemCount() = userList.size
}

