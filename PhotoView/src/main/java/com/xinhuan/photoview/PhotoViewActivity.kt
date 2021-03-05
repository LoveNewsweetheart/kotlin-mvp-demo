package com.xinhuan.photoview

import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.xinhuan.wms.baselibrary.ui.BaseActivity
import com.xinhuan.wms.baselibrary.utils.onClick
import com.xinhuan.wms.baselibrary.widgets.ProgressLoading
import com.xinhuan.wms.demo.provider.router.RouterPath
import com.xinhuan.wms.demo.provider.utils.ImageLoader
import kotlinx.android.synthetic.main.activity_photo_view.*
import org.jetbrains.anko.toast


/**
 * 查看大图
 */

@Route(path = RouterPath.PhotoView.PATCH_PHOTO_VIEW)
class PhotoViewActivity : BaseActivity(){

    lateinit var mLoadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)


        mIvBack.onClick {
            finish()
        }

        val uri = intent.getStringExtra("uri")

        if(uri.isNullOrEmpty()){
            toast("没有相关路径")
            finish()
            return
        }


        if(uri.startsWith("http") || uri.startsWith("https")){
            loadUrlBitmap(uri)
        }else if(uri.startsWith("file")){
            Log.e(this.javaClass.name, "加载本地图片 ...$uri")
            mPhotoView.setImageURI(Uri.parse(uri))
        }else{
            Log.e(this.javaClass.name, "路径不对 ...$uri")
            toast("路径不对")
            finish()
            return
        }

    }

    private fun loadUrlBitmap(url:String){
        mLoadingDialog = ProgressLoading(this)
        mLoadingDialog.showLoading()
        ImageLoader.downloadBitmap(this, url) {
            mLoadingDialog.hideLoading()
            if (it == null){
                toast("请求失败")
                return@downloadBitmap
            }
            mPhotoView.setImageBitmap(it)
        }
    }

}