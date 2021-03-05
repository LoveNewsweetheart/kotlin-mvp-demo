package com.xinhuan.wms.outwarehouse.entity

import android.os.Parcelable
import com.chad.library.adapter.base.entity.MultiItemEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TaskEntity(
    val taskName: String,
    val taskCreateDate: String
) : Parcelable