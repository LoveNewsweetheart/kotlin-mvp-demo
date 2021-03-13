package com.xinhuan.wms.outwarehouse.presenter

import com.xinhuan.wms.baselibrary.presenter.BaseListPresenter
import com.xinhuan.wms.baselibrary.rx.BaseObserverWithRefresh
import com.xinhuan.wms.baselibrary.utils.execute
import com.xinhuan.wms.outwarehouse.entity.ListTaskEntity
import com.xinhuan.wms.outwarehouse.entity.TaskEntity
import com.xinhuan.wms.outwarehouse.service.OutWarehouseService
import com.xinhuan.wms.outwarehouse.view.IGetTaskListView


//如果你的页面是一个列表  那么建议继承  BaseListPresenter
class TaskListPresenter : BaseListPresenter<TaskEntity, IGetTaskListView>() {


    /**
     * 必须重写的请求列表的接口
     */
    override fun getListData(params: HashMap<String, Any>) {
        //如果是一个列表  注意这里调用的是checkRequestListNetWork
        if (!checkRequestListNetWork()) {
            return
        }

        //请求你要调用的接口
        //注意这里是调用的  BaseObserverWithRefresh
        OutWarehouseService().getTaskList(params)
            .execute(object : BaseObserverWithRefresh<ListTaskEntity>(mView) {
                override fun onNext(t: ListTaskEntity) {
                    super.onNext(t)
                    mView.onGetListResult(t.data, t.page)
                }

            }, lifecycleProvider)
    }

//    override fun getListData(params: HashMap<String, Any>) {
//        if (!checkRequestListNetWork()) {
//            return
//        }
//
//        OutWarehouseService().getTaskList(params)
//            .execute(object : BaseObserverWithRefresh<ListTaskEntity>(mView) {
//                override fun onNext(t: ListTaskEntity) {
//                    super.onNext(t)
//                    mView.onGetListResult(t.data, t.page)
//                }
//
//                //默认已经处理了错误信息，但是如果当需要对错误信息额外做一些特殊的处理时，可以在这里回调给Activity做处理
//                override fun onError(e: Throwable) {
//                    super.onError(e)
//                    mView.onGetTestError()
//                }
//
//            }, lifecycleProvider)
//    }


    /**
     * 如果你的界面虽然是一个列表页面，但是你的页面中也有其他的接口需要调用，那么你可以在这里继续写你的其他接口
     * 例如提交等操作
     */
//    fun commitXxx(params: HashMap<String, Any>) {
//        if (!checkNetWork()) {
//            return
//        }
//        //BaseObserverWithDialog -- 请求接口带有Dialog等待过渡对话框
//        OutWarehouseService().commitXxx(params)
//            .execute(object : BaseObserverWithDialog<xxxResult>(mView) {
//                override fun onNext(t: xxxResult) {
//                    super.onNext(t)
//                    mView.onXXX(t.xxx)
//                }
//
//            }, lifecycleProvider)
//    }


}