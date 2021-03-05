package com.xinhuan.wms.demo.usercenter.presenter.view

import com.xinhuan.wms.baselibrary.presenter.view.BaseView

interface IModifyPwdView : BaseView{

    fun onModifyPwdResult(boolean: Boolean)

    fun onSendCheckCodeResult(boolean: Boolean)

}