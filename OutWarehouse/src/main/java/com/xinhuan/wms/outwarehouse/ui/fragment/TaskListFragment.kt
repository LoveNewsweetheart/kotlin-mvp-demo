package com.xinhuan.wms.outwarehouse.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.xinhuan.wms.baselibrary.utils.onClick
import com.xinhuan.wms.baselibrary.utils.toast
import com.xinhuan.wms.demo.provider.entity.RefreshViewManager
import com.xinhuan.wms.demo.provider.ui.BaseListMvpFragment
import com.xinhuan.wms.outwarehouse.R
import com.xinhuan.wms.outwarehouse.entity.TaskEntity
import com.xinhuan.wms.outwarehouse.presenter.TaskListPresenter
import com.xinhuan.wms.outwarehouse.ui.adapter.TaskListAdapter
import com.xinhuan.wms.outwarehouse.view.IGetTaskListView
import kotlinx.android.synthetic.main.fragment_tast_list.*
import kotlinx.android.synthetic.main.layout_common_list.*
import org.greenrobot.eventbus.EventBus

/**
 * 任务列表页
 * 如果你的页面是一个列表  并且需要 实现刷新加载 空布局显示等这样的页面
 * 那么建议继承BaseListMvpFragment 或者BaseListMvpActivity 这样你不需要关心
 * 当数据为空该如何显示页面，你不需要关心刷新如何处理，你也不需要关心上拉加载请求参数以及网络回调后的状态如何处理
 */
class TaskListFragment : BaseListMvpFragment<TaskEntity, TaskListPresenter, IGetTaskListView>(),
    IGetTaskListView,
    OnRefreshListener, OnItemClickListener{

    private val mAdapter = TaskListAdapter(null)
    private var selectedTypeId = -1

    override fun getRefreshViewManager(): RefreshViewManager<TaskEntity> {
        return RefreshViewManager(mRv, mRefreshLayout, mMultiView, mAdapter)
    }

    //返回列表接口需要的参数
    override fun getListRequestParams(): HashMap<String, Any> = params.clear().put("taskType", selectedTypeId).build()


    override fun getV(): IGetTaskListView {
        return this
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_tast_list
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }


    @SuppressLint("SetTextI18n")
    private fun initView() {
        mHeaderBar.getTitleView().text = "任务列表"
        mHeaderBar.getRightView().onClick {
            toast("右侧按钮")
        }

        mAdapter.setOnItemClickListener(this)

    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        toast("点击了$position")
    }

//    /**
//     * 如果你对错误信息提示需要更多的处理
//     * 可以重写该方法，覆盖父类默认的toast提示
//     */
//    override fun showErrorMsg(text: String, status:Int){
//        //在这里处理错误提示
//    }

}