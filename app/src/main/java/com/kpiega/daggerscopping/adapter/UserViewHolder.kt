package com.kpiega.daggerscopping.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.kpiega.daggerscopping.model.User
import kotlinx.android.synthetic.main.row_user_layout.view.*

class UserViewHolder(view: View, val listener: OnUserClickListener) : RecyclerView.ViewHolder(view) {

    fun bind(user: User) {
        itemView.UserChoose.text = user.getFullName()
        itemView.UserChoose.setOnClickListener { _ ->
            listener.onUserClick(user)
        }
    }

}