package com.xinhuan.wms.demo.usercenter.utils

import android.app.Activity
import android.util.Base64
import com.xinhuan.wms.baselibrary.common.AppManager
import com.xinhuan.wms.baselibrary.utils.MD5Util
import com.xinhuan.wms.demo.provider.utils.SpManager
import com.xinhuan.wms.demo.usercenter.ui.activity.LoginActivity
import org.jetbrains.anko.startActivity


fun logoutAndToLogin(activity:Activity){
    SpManager.logout()
    AppManager.instance.finishAllActivity()
    activity.startActivity<LoginActivity>()
}







