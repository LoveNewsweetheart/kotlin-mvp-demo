package com.xinhuan.wms.demo.provider.utils

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule


@GlideModule
class CMAppGlideModule : AppGlideModule(){

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}