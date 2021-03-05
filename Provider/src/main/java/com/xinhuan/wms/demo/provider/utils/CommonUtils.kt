package com.xinhuan.wms.demo.provider.utils


//判断用户是否登录
fun isLogin(): Boolean {
    if (SpManager.getUserId().isEmpty()
    ) {
        return false
    }
    return true
}






