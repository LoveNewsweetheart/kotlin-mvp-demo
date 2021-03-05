package com.xinhuan.wms.baselibrary.utils

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.xinhuan.wms.baselibrary.data.protocol.BaseResp
import com.xinhuan.wms.baselibrary.rx.*
import com.xinhuan.wms.baselibrary.widgets.DefaultTextWatcher
import com.trello.rxlifecycle3.LifecycleProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

//Kotlin通用扩展



/*
    扩展Observable执行
 */
fun <T> Observable<T>.execute(subscriber: BaseObserverWithDialog<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribe(subscriber)
}

fun <T> Observable<T>.execute(subscriber: BaseObserverWithRefresh<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribe(subscriber)
}


fun <T> Observable<T>.execute(subscriber: BaseObserverNoDialog<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribe(subscriber)
}

/**
 *  扩展数据转换
 */
fun <T> Observable<BaseResp<T>>.convert(): Observable<T> {
    return this.flatMap(BaseFunc())
}

/**
 *  扩展Boolean类型数据转换
 */
fun <T> Observable<BaseResp<T>>.convertBoolean(): Observable<Boolean> {
    return this.flatMap(BaseFuncBoolean())
}

/**
 *  扩展点击事件
 */
fun View.onClick(listener:View.OnClickListener):View{
    setOnClickListener(listener)
    return this
}

/**
 * 扩展点击事件，参数为方法(方法可以作为参数传递，传递一个method方法返回值类型为无返回值)
 */
fun View.onClick(method:() -> Unit):View{
    setOnClickListener { method() }
    return this
}

/**
 * 扩展Button可用性
 */
fun Button.enable(et: EditText, method: () -> Boolean){
    val btn = this
    et.addTextChangedListener(object : DefaultTextWatcher(){
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}

/**
 *   扩展视图可见性
 */
fun View.setVisible(visible:Boolean){
    this.visibility = if (visible) View.VISIBLE else View.GONE
}


fun Fragment.toast(message: CharSequence): Toast{
    return Toast
        .makeText(activity?.applicationContext, message, Toast.LENGTH_SHORT)
        .apply {
            if (message.isNotEmpty()) {
                show()
            }
        }
}
