package com.xinhuan.wms.outwarehouse.ui.adapter

import android.annotation.SuppressLint
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xinhuan.wms.outwarehouse.R
import com.xinhuan.wms.outwarehouse.entity.TaskEntity
import kotlinx.android.synthetic.main.item_task_1line.view.*

/**
 * 任务列表Adapter
 */
class TaskListAdapter(data: MutableList<TaskEntity>?) :
    BaseQuickAdapter<TaskEntity, BaseViewHolder>(
        R.layout.item_task_1line, data
    ) {


    @SuppressLint("SetTextI18n")
    override fun convert(holder: BaseViewHolder, item: TaskEntity) {
        holder.itemView.run {
            mTvIndex.text = holder.layoutPosition.toString()
            mTvTaskName.text = item.taskName
            mTvCreateDate.text = item.taskCreateDate
        }
    }

}



