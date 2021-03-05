package com.xinhuan.wms.demo.usercenter.presenter.view

import com.xinhuan.wms.baselibrary.presenter.view.BaseView
import com.xinhuan.wms.demo.usercenter.entity.UserInfoEntity

interface ILoginView : BaseView{

    fun onGetUserInfoResult(result: UserInfoEntity)
}