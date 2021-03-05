package com.xinhuan.wms.demo.provider.entity.event

//整货确认拣货事件
class WhConfirmPickCargoEvent (
    //货位id
    val cargoPlaceId:String,
    //实拣数量
    val resultPickCount:Int)