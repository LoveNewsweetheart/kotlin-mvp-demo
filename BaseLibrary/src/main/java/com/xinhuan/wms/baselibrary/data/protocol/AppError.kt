package com.xinhuan.wms.baselibrary.data.protocol

/**
 * App自定义的异常返回code
 */
enum class AppError(val code: Int, val  msg: String) {

    JSON_EXCEPTION(-10000, "数据解析失败"),
    SOCKET_TIMEOUT_EXCEPTION(10001,"请求超时"),
    SSL_HANDSHAKE_EXCEPTION(-10002,"网络校验失败"),
    CONNECT_EXCEPTION(-10003,"网络连接异常，请检查网络是否可用"),
    HTTP_EXCEPTION(-10004,"无法访问服务器"),
    OTHER_EXCEPTION(-10005,"网络请求失败"),
    REMOTE_DATA_EXCEPTION(-10006,"服务器返回数据出错"),
    NETWORK_DISABLED(-10006,"网络不可用，请检查网络连接")

}