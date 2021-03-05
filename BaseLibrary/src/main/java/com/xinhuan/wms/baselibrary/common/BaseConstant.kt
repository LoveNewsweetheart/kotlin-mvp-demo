package com.xinhuan.wms.baselibrary.common

import com.xinhuan.wms.baselibrary.BuildConfig


class BaseConstant{
    companion object {
        const val BASE_URL = BuildConfig.WMS_BASE_URL

        const val SCAN_BROAD_CAST_NAME = "com.barcode.sendBroadcast"
    }
}