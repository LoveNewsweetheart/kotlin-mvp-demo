package com.xinhuan.wms.demo.provider.ext

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kennyc.view.MultiStateView
import com.xinhuan.wms.demo.provider.R
import com.xinhuan.wms.demo.provider.utils.ImageLoader
import com.xinhuan.wms.demo.provider.widgets.CommonDialog
import org.jetbrains.anko.find

//Kotlin通用扩展


/*
    ImageView加载网络图片
 */
fun ImageView.loadUrl(url: String) {
    ImageLoader.loadImage(context, url, this)
}

/*
    多状态视图开始加载
 */
fun MultiStateView.showLoading() {
    viewState = MultiStateView.ViewState.LOADING
    val loadingView = getView(MultiStateView.ViewState.LOADING)
    val animBackground = loadingView!!.find<View>(R.id.loading_anim_view).background
    (animBackground as AnimationDrawable).start()
}

fun MultiStateView.showError() {
    viewState = MultiStateView.ViewState.ERROR
}


//显示无数据页面，并且设置自定义提示文案
fun MultiStateView.showEmptyData(content:String = "暂无数据", hintContent:String) {
    viewState = MultiStateView.ViewState.EMPTY
    val emptyView = getView(viewState)
    val hintText = emptyView?.findViewById<TextView>(R.id.mTvHint)
    val contentText = emptyView?.findViewById<TextView>(R.id.mTvContent)
    hintText?.run {
        visibility = View.VISIBLE
        text = hintContent
    }
    contentText?.run {
        visibility = View.VISIBLE
        text = content
    }
}

//显示无数据页面，并且设置自定义提示文案
fun MultiStateView.showEmptyData(content:String = "暂无数据") {
    viewState = MultiStateView.ViewState.EMPTY
    val emptyView = getView(viewState)
    val contentText = emptyView?.findViewById<TextView>(R.id.mTvContent)
    contentText?.run {
        visibility = View.VISIBLE
        text = content
    }
}

fun MultiStateView.showContent() {
    viewState = MultiStateView.ViewState.CONTENT
}

fun MultiStateView.setOnRetryCallback(method: () -> Unit) {
    val errorView = getView(MultiStateView.ViewState.ERROR)

    errorView?.let {
        it.setOnClickListener { method() }
    }
}


/**
 * 显示一个按钮的对话框  监听确认按钮
 */
fun Activity.showAlertDialog(alertContent: String, confirmMethod: () -> Unit = {}): Dialog {
    val dialog = CommonDialog(this)
    dialog.setContent(alertContent)
    dialog.setRightText("确定")
    dialog.setCancelable(false)
    dialog.setOnDialogClickListener(object : CommonDialog.DefaultDialogClickListener() {
        override fun onRightClick() {
            confirmMethod()
        }
    })
    dialog.show()
    return dialog
}

/**
 * 显示一个按钮的对话框 并监听确认按钮和监听对话框消失
 */
fun Activity.showAlertDialog(alertContent: String, confirmMethod: () -> Unit = {}, dismissCallback:()->Unit): Dialog {
    val dialog = CommonDialog(this)
    dialog.setContent(alertContent)
    dialog.setRightText("确定")
    dialog.setCancelable(false)
    dialog.setOnDialogClickListener(object : CommonDialog.DefaultDialogClickListener() {
        override fun onRightClick() {
            confirmMethod()
        }
    })

    dialog.setOnDismissListener {
        dismissCallback()
    }
    dialog.show()
    return dialog
}

/**
 * 显示两个按钮的对话框 并且可以设置自定义左侧按钮文字和右侧按钮文字，并监听右侧监听按钮
 */
fun Activity.showAlertDialog(alertContent: String, leftText: String, rightText: String, rightMethod: () -> Unit = {}): Dialog {
    val dialog = CommonDialog(this)
    dialog.setContent(alertContent)
    dialog.setLeftText(leftText)
    dialog.setRightText(rightText)
    dialog.setCancelable(false)
    dialog.setOnDialogClickListener(object : CommonDialog.DefaultDialogClickListener() {
        override fun onRightClick() {
            rightMethod()
        }
    })
    dialog.show()
    return dialog
}




fun Fragment?.hideSoftInput(editText: EditText) {
    if (this == null) {
        return
    }
    Handler().postDelayed({
        if (activity?.currentFocus != null) {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(editText.windowToken, 0)//强制隐藏键盘
        }
    }, 200)

}


//RecyclerView滚动到指定位置，并且尽量让当前的index在最上方
fun RecyclerView.rvScrollToPosition(index: Int) {
    scrollToPosition(index)
    if (layoutManager is LinearLayoutManager) {
        (layoutManager as LinearLayoutManager).scrollToPositionWithOffset(index, 0)
    }
}








