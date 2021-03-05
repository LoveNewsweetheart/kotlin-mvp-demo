package com.xinhuan.wms.baselibrary.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import com.xinhuan.wms.baselibrary.common.AppManager
import com.xinhuan.wms.baselibrary.utils.RequestParams
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import org.jetbrains.anko.find


/*
    Activity基类，业务无关
 */
open class BaseActivity : RxAppCompatActivity() {

    //网络请求的参数
    val params = RequestParams()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setConfig()
        AppManager.instance.addActivity(this)
    }

    protected fun setConfig() {
        //设置竖屏
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }



    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }


    //获取Window中视图content
    val contentView: View
        get() {
            val content = find<FrameLayout>(android.R.id.content)
            return content.getChildAt(0)
        }

}
