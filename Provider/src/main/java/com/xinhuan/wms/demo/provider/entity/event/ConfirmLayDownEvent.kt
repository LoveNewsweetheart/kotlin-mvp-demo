package com.xinhuan.wms.demo.provider.entity.event

/**
 * 确认落放位完成事件
 */
class ConfirmLayDownEvent(
    /**
     * isLastOne 是否全部完成，false 货位列表中还有未拣完的货物，中途落放托盘。true货物都已经拣完
     */
    val isLastOne:Boolean
)