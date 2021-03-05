package com.xinhuan.wms.demo.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.xinhuan.wms.demo.R
import com.xinhuan.wms.demo.provider.router.RouterPath
import com.xinhuan.wms.demo.provider.utils.isLogin
import com.xinhuan.wms.demo.ui.activity.MainActivity
import com.xinhuan.wms.demo.usercenter.ui.activity.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!isTaskRoot) {
            val intentAction = intent.action;
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intentAction != null && intentAction == Intent.ACTION_MAIN) {
                finish()
                return
            }
        }

        setContentView(R.layout.activity_splash)

        if (isLogin()) {
            //ARouter.getInstance().build(RouterPath.OutWarehouse.PATH_TASK).navigation()
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            //去登录页面
//            ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()

    }


}
