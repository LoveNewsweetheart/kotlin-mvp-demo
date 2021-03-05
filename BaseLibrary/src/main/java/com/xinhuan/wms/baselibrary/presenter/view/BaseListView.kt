package com.xinhuan.wms.baselibrary.presenter.view

import com.xinhuan.wms.baselibrary.data.protocol.Page

/*
    MVP中带有列表的视图回调 基类
 */
interface BaseListView<E> : BaseView {

    //请求列表数据成功回调
    fun onGetListResult(list:MutableList<E>?, page:Page? = null)

    //控制刷新界面的显示状态
    fun onShowRefreshUi()

    //控制刷新控件恢复原态
    fun onHindRefreshUi()

    //列表接口异常
    fun onListError(text:String, status:Int)
}
