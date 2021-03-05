package com.xinhuan.wms.baselibrary

import android.app.Application

open class BaseApplication : Application(){

    companion object{
        lateinit var instant :BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instant = this
    }



}