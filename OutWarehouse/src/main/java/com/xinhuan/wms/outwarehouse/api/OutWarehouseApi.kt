package com.xinhuan.wms.outwarehouse.api

import com.xinhuan.wms.baselibrary.data.protocol.BaseResp
import com.xinhuan.wms.outwarehouse.entity.ListTaskEntity
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST


interface OutWarehouseApi {


    //获取任务列表
    @POST("out/pda/list")
    fun getTaskList(@Body params: HashMap<String, Any>): @JvmSuppressWildcards Observable<BaseResp<ListTaskEntity>>



}

