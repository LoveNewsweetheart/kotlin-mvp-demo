package com.xinhuan.wms.baselibrary.utils

//网络请求参数
class RequestParams {
    private val params = HashMap<String, Any>()

    fun put(key:String, value:Any):RequestParams{
        params[key] = value
        return this
    }

    fun build() : HashMap<String, Any>{
        return params
    }

    fun clear():RequestParams{
        params.clear()
        return this
    }

}