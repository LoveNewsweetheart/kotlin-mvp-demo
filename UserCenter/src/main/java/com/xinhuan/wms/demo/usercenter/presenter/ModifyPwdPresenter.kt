package com.xinhuan.wms.demo.usercenter.presenter

import com.xinhuan.wms.baselibrary.presenter.BasePresenter
import com.xinhuan.wms.baselibrary.rx.BaseObserverWithDialog
import com.xinhuan.wms.baselibrary.utils.execute
import com.xinhuan.wms.demo.usercenter.presenter.view.IModifyPwdView
import com.xinhuan.wms.demo.usercenter.service.UserService

class ModifyPwdPresenter : BasePresenter<IModifyPwdView>() {



    fun modifyPwd(params: HashMap<String, Any>) {
        if (!checkNetWork()) {
            return
        }
        UserService().modifyPwd(params).execute(object : BaseObserverWithDialog<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onModifyPwdResult(t)
            }
        }, lifecycleProvider)
    }


}