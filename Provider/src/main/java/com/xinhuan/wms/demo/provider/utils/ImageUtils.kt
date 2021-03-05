package com.xinhuan.wms.demo.provider.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.Exception
import java.lang.RuntimeException

object ImageLoader{

    var placeholderId:Int = 0
    var errorId:Int = 0

    fun init(placeholderId:Int, errorId:Int){
        this.placeholderId = placeholderId
        this.errorId = errorId
    }

    fun loadImage(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url).into(imageView)
    }

    fun loadImageWithDefault(context: Context, url: String, imageView: ImageView) {
        if (placeholderId == 0 || errorId == 0) {
            throw RuntimeException("使用前，请调用init方法初始化")
        }
        Glide.with(context)
            .load(url)
            .placeholder(placeholderId)
            .error(errorId)
            .into(imageView)

    }

    fun loadImageFitCenter(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url).fitCenter().into(imageView)
    }

    fun loadGif(context: Context, url: String, imageView: ImageView){
        Glide.with(context)
            .asGif()
            .load(url)
            .into(imageView)
    }

    /**
     * 下载原始尺寸图片
     */
    fun downloadBitmap(context:Context,url :String,method: (bitmap:Bitmap?) -> Unit){
        doAsync {
            try {
                val bitmap = Glide.with(context).asBitmap().load(url).into(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL).get()
                uiThread {
                    method(bitmap)
                }
            }catch (ex:Exception){
                ex.printStackTrace()
                uiThread {
                    method(null)
                }
            }


        }
    }




}