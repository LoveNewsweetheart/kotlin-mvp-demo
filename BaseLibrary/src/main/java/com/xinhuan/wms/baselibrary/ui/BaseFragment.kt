package com.xinhuan.wms.baselibrary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.xinhuan.wms.baselibrary.utils.RequestParams
import com.trello.rxlifecycle3.components.support.RxFragment


/*
    Fragment基类，业务无关
 */
abstract  class BaseFragment : RxFragment(){

    val params = RequestParams()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    @LayoutRes
    abstract fun getLayoutId():Int
}


