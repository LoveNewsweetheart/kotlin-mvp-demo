package com.xinhuan.wms.outwarehouse.view

import com.xinhuan.wms.baselibrary.presenter.view.BaseListView
import com.xinhuan.wms.outwarehouse.entity.TaskEntity


interface IGetTaskListView : BaseListView<TaskEntity> {

    //如果你的页面除了列表接口  还有其他的接口例如提交等操作的接口
    //那么你的回调可以在这里添加
    //fun xxx ()

}