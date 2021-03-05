package com.xinhuan.wms.demo.provider.entity.event

/**
 * 散货 本趟完成事件
 */
class ScFinishPickEvent(
    /**
     *  0干完了 没有二趟了   1有二趟
     */
    val isDoAll:String
)