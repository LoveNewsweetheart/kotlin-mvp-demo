package com.xinhuan.wms.baselibrary.rx

import com.xinhuan.wms.baselibrary.presenter.view.BaseListView
import io.reactivex.disposables.Disposable

/**
 * Rx订阅者默认实现
 * 主要用于列表处理刷新和加载的状态处理
 */
open class BaseObserverWithRefresh<T>(private val baseListView: BaseListView<*>) : BaseObserverNoDialog<T>(baseListView){
    override fun onComplete() {
        super.onComplete()
        baseListView.onHindRefreshUi()
    }

    override fun onSubscribe(d: Disposable) {
        super.onSubscribe(d)
        baseListView.onShowRefreshUi()
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
        super.onError(e)
        e.printStackTrace()
        baseListView.onHindRefreshUi()
    }

    override fun handleError(msg: String, status: Int) {
        baseListView.onListError(msg, status)
    }

}