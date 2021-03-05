package com.xinhuan.wms.demo.usercenter.entity

data class UserInfoEntity(
    val userId:String,//用户id
    val userName:String = "",
    val userAge:Int,
    val userAccount:String
)