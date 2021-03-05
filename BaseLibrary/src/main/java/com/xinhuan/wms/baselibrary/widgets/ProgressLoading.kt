package com.xinhuan.wms.baselibrary.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import com.xinhuan.wms.baselibrary.R
import org.jetbrains.anko.find

/*
    加载对话框封装
 */
class ProgressLoading(context: Context) : Dialog(context, R.style.LightProgressDialog) {

    private var animDrawable: AnimationDrawable? = null

    init {
        //样式引入
        //设置布局
        setContentView(R.layout.progress_dialog)
        setCancelable(true)
        setCanceledOnTouchOutside(false)
        window?.attributes?.gravity = Gravity.CENTER

        setCancelable(false)

        val lp = window?.attributes
        lp?.dimAmount = 0.2f
        //设置属性
        this.window?.attributes = lp

        //获取动画视图
        val loadingView = find<ImageView>(R.id.iv_loading)
        animDrawable = loadingView.background as AnimationDrawable

    }

    /*
        显示加载对话框，动画开始
     */
    fun showLoading() {
        if (!isShowing) {
            super.show()
            animDrawable?.start()
        }
    }

    /*
        隐藏加载对话框，动画停止
     */
    fun hideLoading() {
        if (isShowing) {
            super.dismiss()
            animDrawable?.stop()
        }
    }
}

