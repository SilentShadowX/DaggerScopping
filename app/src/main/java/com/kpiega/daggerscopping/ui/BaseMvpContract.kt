package com.kpiega.daggerscopping.ui

interface BaseMvpContract {

    interface BaseView {
        fun showMessage(message: String)
    }

    interface BasePresener<V: BaseView> {
        var view: V?
        fun attachView(view: V)
        fun detachView()
    }
}