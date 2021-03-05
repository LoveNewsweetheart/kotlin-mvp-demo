package com.xinhuan.wms.demo

import com.xinhuan.wms.demo.provider.BaseProviderApplication


class AppApplication : BaseProviderApplication() {


    lateinit var instant: AppApplication

    override fun onCreate() {
        super.onCreate()
        instant = this

        initBugly()

    }


    private fun initBugly(){
       //...
    }

}