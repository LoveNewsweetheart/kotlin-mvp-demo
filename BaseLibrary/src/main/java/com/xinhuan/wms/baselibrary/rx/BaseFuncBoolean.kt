package com.xinhuan.wms.baselibrary.rx


import com.xinhuan.wms.baselibrary.data.protocol.BaseResp
import io.reactivex.Observable
import io.reactivex.functions.Function

/*
    Boolean类型转换封装
 */
class BaseFuncBoolean<T>: Function<BaseResp<T>, Observable<Boolean>> {
    override fun apply(t: BaseResp<T>): Observable<Boolean> {
        if (t.status != BaseResp.SUCCESS){
            return Observable.error(BaseException(t.status, t.msg))
        }
        return Observable.just(true)
    }
}
