package com.xinhuan.wms.baselibrary.presenter

import android.content.Context
import com.xinhuan.wms.baselibrary.data.protocol.AppError
import com.xinhuan.wms.baselibrary.presenter.view.BaseView
import com.xinhuan.wms.baselibrary.utils.NetWorkUtils
import com.trello.rxlifecycle3.LifecycleProvider

/*
    MVP中P层 基类
 */
open class BasePresenter<V: BaseView>{

    lateinit var mView:V
    lateinit var lifecycleProvider: LifecycleProvider<*>
    lateinit var context:Context

    /**
     *   检查网络是否可用
     */
    fun checkNetWork():Boolean{
        if(NetWorkUtils.isNetWorkAvailable(context)){
            return true
        }
        mView.onError(AppError.NETWORK_DISABLED.msg, AppError.NETWORK_DISABLED.code)
        return false
    }


}
