package com.xinhuan.wms.outwarehouse.entity

import com.xinhuan.wms.baselibrary.data.protocol.Page
import com.xinhuan.wms.outwarehouse.entity.TaskEntity

data class ListTaskEntity(
    val data: MutableList<TaskEntity> = mutableListOf(),
    val page: Page? = null
)