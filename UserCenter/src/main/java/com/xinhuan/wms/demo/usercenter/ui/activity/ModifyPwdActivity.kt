package com.xinhuan.wms.demo.usercenter.ui.activity

import android.os.Bundle
import android.os.CountDownTimer
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.xinhuan.wms.baselibrary.ui.BaseMvpActivity
import com.xinhuan.wms.baselibrary.utils.onClick
import com.xinhuan.wms.demo.provider.router.RouterPath
import com.xinhuan.wms.demo.usercenter.R
import com.xinhuan.wms.demo.usercenter.presenter.ModifyPwdPresenter
import com.xinhuan.wms.demo.usercenter.presenter.view.IModifyPwdView
import com.xinhuan.wms.demo.usercenter.utils.logoutAndToLogin
import kotlinx.android.synthetic.main.activity_modify_pwd.*
import org.jetbrains.anko.toast

@Route(path = RouterPath.UserCenter.PATH_MODIFY_PWD)
class ModifyPwdActivity : BaseMvpActivity<ModifyPwdPresenter, IModifyPwdView>(), IModifyPwdView{

    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_pwd)
        

        mBtnConfirm.onClick {
            commitModify()
        }

        mTvSendCheckCode.onClick {
        }

    }


    private fun commitModify(){

        //密码校验
        val newPwd1 = mEdtNewPwd1.text.toString()
        if (newPwd1.isEmpty()) {
            toast("请输入新密码")
            return
        }
        if (newPwd1.length != 8) {
            toast("请输入8位数字组成的密码")
            return
        }
          val newPwd2 = mEdtNewPwd2.text.toString()
        if (newPwd2.isEmpty()) {
            toast("请输入确认密码")
            return
        }

        if (newPwd1 != newPwd2) {
            toast("两次密码输入不一致")
            return
        }


        //请求后台接口并传入参数
        mPresenter.modifyPwd(params.clear().put("password", newPwd1).build())

    }



    override fun getV(): IModifyPwdView {
        return this
    }


    override fun onModifyPwdResult(boolean: Boolean) {
        toast("修改成功")
        logoutAndToLogin(this)
    }

    override fun onSendCheckCodeResult(boolean: Boolean) {
        toast("验证码已发送")
    }



}