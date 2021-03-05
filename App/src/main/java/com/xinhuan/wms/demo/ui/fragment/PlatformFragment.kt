package com.xinhuan.wms.demo.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.xinhuan.wms.baselibrary.ui.BaseFragment
import com.xinhuan.wms.baselibrary.utils.onClick
import com.xinhuan.wms.baselibrary.utils.toast
import com.xinhuan.wms.demo.R
import com.xinhuan.wms.demo.provider.router.RouterPath
import kotlinx.android.synthetic.main.fragment_platform.*

class PlatformFragment : BaseFragment(){

    override fun getLayoutId(): Int {
        return R.layout.fragment_platform
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }



    @SuppressLint("SetTextI18n")
    private fun initView() {

        //商品清点回调
        mLayoutInCount.onClick{
            toast("商品清点")
        }

        //商品验收回调
        mLayoutInCheck.onClick {
            ARouter.getInstance().build(RouterPath.InWarehouse.IN_CHECK).navigation()
        }

        mLayoutMove.onClick {
            ARouter.getInstance().build(RouterPath.WarehouseManager.LOCATION_MOVE).navigation()
        }

    }

}
