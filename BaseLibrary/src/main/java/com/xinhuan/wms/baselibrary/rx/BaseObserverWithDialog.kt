package com.xinhuan.wms.baselibrary.rx

import android.util.Log
import com.xinhuan.wms.baselibrary.presenter.view.BaseView
import io.reactivex.disposables.Disposable

/**
 * Rx订阅者默认实现
 * 带有请求过渡对话框
 */
open class BaseObserverWithDialog<T>(private val baseView: BaseView) : BaseObserverNoDialog<T>(baseView){
    override fun onComplete() {
        super.onComplete()
        baseView.hideLoading()
    }

    override fun onSubscribe(d: Disposable) {
        super.onSubscribe(d)
        baseView.showLoading()
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
        super.onError(e)
        Log.e("BaseObserverWithDialog", " ---- onError-----")
        e.printStackTrace()
        baseView.hideLoading()
    }
}