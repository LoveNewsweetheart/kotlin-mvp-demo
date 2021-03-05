package com.xh.warehousemanager.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.xh.warehousemanager.R
import com.xinhuan.wms.baselibrary.ui.BaseActivity
import com.xinhuan.wms.baselibrary.utils.onClick
import com.xinhuan.wms.demo.provider.ext.loadUrl
import com.xinhuan.wms.demo.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_location_move.*

@Route(path = RouterPath.WarehouseManager.LOCATION_MOVE)
class LocationMoveActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_move)


        val url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604586228166&di=007b4ea607a9678fcadfaaaf2b243f2e&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%3D580%2Fsign%3D3cd97f188c1001e94e3c1407880f7b06%2Fbbb59e1001e93901be2abbcf7aec54e737d19687.jpg"

        mIvPhoto.loadUrl(url)

        //查看大图
        mBtnLookBig.onClick {
            ARouter.getInstance().build(RouterPath.PhotoView.PATCH_PHOTO_VIEW)
                .withString("uri", url)
                .navigation()
        }
    }
}