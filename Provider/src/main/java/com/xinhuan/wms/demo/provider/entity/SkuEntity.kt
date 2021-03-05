package com.xinhuan.wms.demo.provider.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SkuEntity(
    val checkTaskDetailId:String?="",//后台需要
    var isAdd:Int = 0,//传给后台需要字段 是否新添加的商品 0否 1是
    val skuId: String? = "",
    val skuName: String = "",
    val skuUnit: String = "",//单位
    val skuNorm:String?= "",//规格
    val skuProductionDate: String? = "",//生产日期
    val stock: Int = 0,//可移数量 (货位转移使用)
    val commitStatus: Int = 0,
    val batchCode: String? = "",//批次号 后台需要
    var skuNum: Int = 0, // 盘点数量
    val barCodes:ArrayList<String>?,//商品条码
    val locationCode:String?="",//货位号码
    val goodsStates:String? = "",//商品状态 ： "上架"或"下架"
    val selected: Int = 0 //是否在UI默认选中状态 1 :选中（扫描一码多品时列表中选择会用到）
) : Parcelable {

    class CommitStatus {
        companion object {
            //已提交
            const val COMMIT = 1

            //未提交
            const val UNCOMMITTED = 0
        }
    }
}