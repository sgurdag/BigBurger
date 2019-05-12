package com.smartover.bigburger.data.mvp.view

interface BaseView {
    fun showProgresss()
    fun dismissProgresss()
    fun checkInternet(): Boolean
    fun connectionError()
}