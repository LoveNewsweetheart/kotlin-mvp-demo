package com.xinhuan.wms.inwarehouse.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.xinhuan.wms.baselibrary.ui.BaseActivity
import com.xinhuan.wms.demo.provider.router.RouterPath
import com.xinhuan.wms.inwarehouse.R

/**
 * 入库模块  商品验收页面
 */
@Route(path = RouterPath.InWarehouse.IN_CHECK)
class InCheckActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_check)
    }

}