package com.xinhuan.wms.outwarehouse.service

import com.xinhuan.wms.baselibrary.utils.convert
import com.xinhuan.wms.outwarehouse.entity.ListTaskEntity
import com.xinhuan.wms.outwarehouse.respository.OutWarehouseRepository
import io.reactivex.Observable


/**
 * 出库
 */
class OutWarehouseService {



    fun getTaskList(params: HashMap<String, Any>): Observable<ListTaskEntity> {
        return OutWarehouseRepository().getTaskList(params).convert()

    }


}
