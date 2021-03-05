package com.xinhuan.wms.baselibrary.data.protocol

/*
    能用响应对象
    @status:响应状态码
    @message:响应文字消息
    @data:具体响应业务对象
 */
data class BaseResp<out T>(val status:Int, val msg:String, val content:T){
    companion object{
        const val SUCCESS = 0
    }
}
