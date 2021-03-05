package com.xinhuan.wms.baselibrary.rx

import com.google.gson.JsonSyntaxException
import com.xinhuan.wms.baselibrary.data.protocol.AppError
import com.xinhuan.wms.baselibrary.presenter.view.BaseView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.adapter.rxjava2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException

/**
 * Rx订阅者默认实现
 */
open class BaseObserverNoDialog<T>(private val baseView: BaseView) : Observer<T>{
    override fun onComplete() {
       // Log.e("BaseObserverNoDialog", " ---- onComplete-----")
    }

    override fun onSubscribe(d: Disposable) {
       // Log.e("BaseObserverNoDialog", " ---- onSubscribe-----")
    }

    override fun onNext(t: T) {
       // Log.e("BaseObserverNoDialog", " ---- onNext-----")
    }

    override fun onError(e: Throwable) {
       // Log.e("BaseObserverNoDialog", " ---- onError-----")
        e.printStackTrace()
        when (e) {
            is BaseException -> handleError(e.msg, e.status)
            is JsonSyntaxException -> handleError(AppError.JSON_EXCEPTION.msg, AppError.JSON_EXCEPTION.code)
            is SocketTimeoutException -> handleError(AppError.SOCKET_TIMEOUT_EXCEPTION.msg, AppError.SOCKET_TIMEOUT_EXCEPTION.code)
            is SSLHandshakeException -> handleError(AppError.SSL_HANDSHAKE_EXCEPTION.msg, AppError.SSL_HANDSHAKE_EXCEPTION.code)
            is ConnectException -> handleError(AppError.CONNECT_EXCEPTION.msg, AppError.CONNECT_EXCEPTION.code)
            else -> handleError(AppError.OTHER_EXCEPTION.msg, AppError.OTHER_EXCEPTION.code)
        }
    }

    open fun handleError(msg:String, status:Int){
        baseView.onError(msg, status)
    }

}