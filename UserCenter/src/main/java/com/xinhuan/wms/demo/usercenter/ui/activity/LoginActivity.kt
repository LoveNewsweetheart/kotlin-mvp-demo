package com.xinhuan.wms.demo.usercenter.ui.activity

import android.os.Bundle
import android.text.Editable
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.xinhuan.wms.baselibrary.ui.BaseMvpActivity
import com.xinhuan.wms.baselibrary.utils.onClick
import com.xinhuan.wms.baselibrary.utils.setVisible
import com.xinhuan.wms.baselibrary.widgets.DefaultTextWatcher
import com.xinhuan.wms.demo.provider.ext.showAlertDialog
import com.xinhuan.wms.demo.provider.router.RouterPath
import com.xinhuan.wms.demo.provider.utils.SpManager
import com.xinhuan.wms.demo.usercenter.R
import com.xinhuan.wms.demo.usercenter.entity.UserInfoEntity
import com.xinhuan.wms.demo.usercenter.presenter.LoginPresenter
import com.xinhuan.wms.demo.usercenter.presenter.view.ILoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast


@Route(path = RouterPath.UserCenter.PATH_LOGIN)
class LoginActivity : BaseMvpActivity<LoginPresenter, ILoginView>(), ILoginView {

    private var userEntity: UserInfoEntity? = null

    override fun getV(): ILoginView {
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        SpManager.logout()

        initView()

        //弹框提示
        showAlertDialog("该app由新欢独家开发")
    }


    private fun initView() {

        if (SpManager.getUserAccount().isNotEmpty()) {
            mEdtAccount.setText(SpManager.getUserAccount())
            mEdtAccount.setSelection(mEdtAccount.text.toString().length)
            mIvCleanAccount.setVisible(mEdtAccount.text != null && mEdtAccount.text.isNotEmpty())
        }

        mEdtAccount.addTextChangedListener(object : DefaultTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                super.afterTextChanged(s)
                if (s == null || s.isEmpty()) {
                    mIvCleanAccount.setVisible(false)
                    return
                }
                mIvCleanAccount.setVisible(true)
            }
        })
        mEdtPwd.addTextChangedListener(object : DefaultTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                super.afterTextChanged(s)
                if (s == null || s.isEmpty()) {
                    mIvCleanPwd.setVisible(false)
                    return
                }
                mIvCleanPwd.setVisible(true)
            }
        })

        mIvCleanAccount.onClick {
            mEdtAccount.setText("")
            mEdtPwd.setText("")
            mEdtAccount.requestFocus()
            mIvCleanAccount.setVisible(false)
            mIvCleanPwd.setVisible(false)
        }

        mIvCleanPwd.onClick {
            mEdtPwd.setText("")
            mIvCleanPwd.setVisible(false)
        }

        mBtnLogin.onClick {
            login()
        }

    }


    private fun login() {
        val account = mEdtAccount.text.toString().trim()
        if (account.isEmpty()) {
            toast("请输入账号")
            return
        }
        val pwd = mEdtPwd.text.toString().trim()
        if (pwd.isEmpty()) {
            toast("请输入密码")
            return
        }

        //请求后端接口
        mPresenter.userLogin(
            params.clear()
                .put("loginName", account)
                .put("password", pwd)
                .build()
        )
    }


    /**
     * 登录后回调
     */
    override fun onGetUserInfoResult(result: UserInfoEntity) {
        this.userEntity = result
        //将数据保存到SharedPreferences中
        SpManager.setUserId(result.userId)
        SpManager.setUserAccount(result.userAccount)
        SpManager.setUserName(result.userName)
        //Toast提示
        toast("登录成功")
        finish()
        ARouter.getInstance().build(RouterPath.App.MAIN_ACTIVITY).navigation()
    }



}
