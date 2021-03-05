package com.xinhuan.wms.demo.usercenter.presenter

import com.xinhuan.wms.baselibrary.presenter.BasePresenter
import com.xinhuan.wms.baselibrary.rx.BaseObserverWithDialog
import com.xinhuan.wms.baselibrary.utils.execute
import com.xinhuan.wms.demo.usercenter.entity.UserInfoEntity
import com.xinhuan.wms.demo.usercenter.presenter.view.ILoginView
import com.xinhuan.wms.demo.usercenter.service.UserService

class LoginPresenter : BasePresenter<ILoginView>() {

    fun userLogin(params: HashMap<String, Any>) {
        if (!checkNetWork()) {
            return
        }

        UserService().userLogin(params).execute(object : BaseObserverWithDialog<UserInfoEntity>(mView) {
            override fun onNext(t: UserInfoEntity) {
                mView.onGetUserInfoResult(t)
            }
        }, lifecycleProvider)
    }


}