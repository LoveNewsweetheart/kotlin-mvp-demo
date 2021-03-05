package com.xinhuan.wms.demo.provider

import android.annotation.SuppressLint
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.squareup.leakcanary.LeakCanary
import com.xinhuan.wms.baselibrary.BaseApplication
import com.xinhuan.wms.baselibrary.data.net.RetrofitFactory
import com.xinhuan.wms.demo.provider.utils.SpManager


open class BaseProviderApplication : BaseApplication() {

    /*
        全局伴生对象
     */
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instant: BaseProviderApplication
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        init {
            //设置第三方开源刷新框架的全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                layout.setPrimaryColorsId(android.R.color.white, R.color.text_normal)//全局设置主题颜色
                ClassicsHeader(context)//指定为经典Header，默认是 贝塞尔雷达Header
            }
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
                //指定为经典Footer，默认是 BallPulseFooter
                ClassicsFooter(context).setDrawableSize(20f)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        instant = this
        context = this
        initLeakCanary()

        ARouter.openLog()    // 打印日志
        ARouter.openDebug()
        ARouter.init(this)


        //初始化Retrofit， 并添加网络请求的header
        val headers = HashMap<String,String>()
        headers["client"] = "android"
        headers["token"] = SpManager.getToken()
        RetrofitFactory.instance.init(headers)

    }


    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)

    }



}