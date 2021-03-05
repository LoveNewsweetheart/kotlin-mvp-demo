package com.xinhuan.wms.baselibrary.rx

import com.xinhuan.wms.baselibrary.data.protocol.BaseResp
import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 *   通用数据类型转换封装
 */
class BaseFunc<T>: Function<BaseResp<T>, Observable<T>> {
    override fun apply(t: BaseResp<T>): Observable<T> {
        if (t.status != BaseResp.SUCCESS){
            return Observable.error(BaseException(t.status,t.msg))
        }
        return Observable.just(t.content)
    }
}
