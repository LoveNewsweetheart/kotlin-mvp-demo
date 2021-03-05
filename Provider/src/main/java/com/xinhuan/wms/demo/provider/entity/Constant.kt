package com.xinhuan.wms.demo.provider.entity

class Constant {


    class Transfer{
        companion object{
            const val TRANSFER_PLACE = 202// 货位转移
            const val TRANSFER_DISABLED = 203// 正转残
            const val TRANSFER_EXPIRE = 204// 正转过期

            fun getNameByType(type:Int):String{
                return when (type) {
                    TRANSFER_PLACE -> {
                        "货位转移"
                    }
                    TRANSFER_DISABLED -> {
                        "正品转残"
                    }
                    TRANSFER_EXPIRE -> {
                        "正转过期"
                    }
                    else -> "未知类型"
                }
            }
        }

    }



}