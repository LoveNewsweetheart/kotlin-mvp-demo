package com.xinhuan.wms.baselibrary.presenter

import android.content.Context
import com.xinhuan.wms.baselibrary.presenter.view.BaseListView
import com.xinhuan.wms.baselibrary.utils.NetWorkUtils
import com.trello.rxlifecycle3.LifecycleProvider

/**
 * 带有列表页面的MVP中P层 基类
 */
abstract class BaseListPresenter<E, V: BaseListView<E>> {
    abstract fun getListData(params:HashMap<String,Any>)

    lateinit var mView: V
    lateinit var lifecycleProvider: LifecycleProvider<*>
    lateinit var context: Context


    /**
     *   检查网络是否可用
     */
    fun checkNetWork():Boolean{
        if(NetWorkUtils.isNetWorkAvailable(context)){
            return true
        }
        mView.onError("网络不可用，请检查网络连接", -5)
        return false
    }

    /**
     *   请求列表接口时，检查网络是否可用
     */
    fun checkRequestListNetWork():Boolean{
        if(NetWorkUtils.isNetWorkAvailable(context)){
            return true
        }
        mView.onListError("网络不可用，请检查网络连接", -5)
        return false
    }

}