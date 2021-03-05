package com.xinhuan.wms.baselibrary.data.protocol

data class ListEntity<T>(
    val data: MutableList<T> = mutableListOf(),
    val page: Page? = null
)