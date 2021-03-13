package com.xinhuan.wms.demo.provider.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.xinhuan.wms.baselibrary.data.protocol.Page
import com.xinhuan.wms.baselibrary.presenter.BaseListPresenter
import com.xinhuan.wms.baselibrary.presenter.view.BaseListView
import com.xinhuan.wms.baselibrary.ui.BaseFragment
import com.xinhuan.wms.baselibrary.utils.ObjUtils
import com.xinhuan.wms.baselibrary.utils.toast
import com.xinhuan.wms.baselibrary.widgets.ProgressLoading
import com.xinhuan.wms.demo.provider.entity.RefreshViewManager
import com.xinhuan.wms.demo.provider.ext.*
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener

abstract class BaseListMvpFragment<E, P : BaseListPresenter<E, V>, V : BaseListView<E>>  : BaseFragment(), BaseListView<E>,
    OnRefreshListener, OnLoadMoreListener {

    lateinit var mPresenter: P
    private lateinit var mLoadingDialog: ProgressLoading
    var  isLoading = false//是否正处于网络加载中

    private lateinit var refManager: RefreshViewManager<E>
    private var currentPage = 0//从0开始


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init(){
        //初始加载过渡框
        mLoadingDialog = ProgressLoading(activity!!)

        bindPresenter()

        refManager = getRefreshViewManager()

        initRecyclerView()
        refManager.refreshLayout.setOnRefreshListener(this)
        refManager.refreshLayout.setOnLoadMoreListener(this)

        if(isInitData()){
            getFirstList()
        }

        refManager.multiStateView.setOnRetryCallback {
            //点击屏幕重试回调（只有在网络请求失败而出现失败页面时）
            getListData()
        }
    }

    /**
     * 是否进入页面后就自动加载第一页数据
     */
    open fun isInitData():Boolean{
        return true
    }

    protected fun getFirstList(){
        refManager.baseQuickAdapter.setNewInstance(null)
        refManager.multiStateView.showLoading()
        getListData()
    }

    private fun getListData(){
        mPresenter.getListData(getListRequestParams())
    }

    private fun getListData(currentPage:Int){
        val params = getListRequestParams()
        params["currentPage"] = currentPage + 1
        mPresenter.getListData(params)
    }

    open protected fun initRecyclerView(){
        refManager.recyclerView.layoutManager = LinearLayoutManager(activity)
        refManager.recyclerView.adapter = refManager.baseQuickAdapter
    }

    private fun bindPresenter() {
        mPresenter = ObjUtils.getObj(this, 1)
        mPresenter.mView = getV()
        mPresenter.lifecycleProvider = this
        mPresenter.context = activity!!
    }

    override fun showLoading() {
        isLoading = true
        if (isDetached) {
            return
        }
        mLoadingDialog.showLoading()
    }

    override fun onShowRefreshUi() {
        isLoading = true
        if (isDetached) {
            return
        }
        if (refManager.baseQuickAdapter.data.isEmpty()) {
            refManager.multiStateView.showLoading()
        }
    }

    override fun onHindRefreshUi() {
        isLoading = false
        if (isDetached) {
            return
        }
        refManager.refreshLayout.finishRefresh()
        refManager.refreshLayout.finishLoadMore()
    }
    /*
        隐藏加载框，默认实现
     */
    override fun hideLoading() {
        isLoading = false
        if (isDetached) {
            return
        }
        mLoadingDialog.hideLoading()
    }

    /**
     * 列表接口错误回调
     */
    override fun onListError(text: String, status: Int) {
        isLoading = false
        if (isDetached) {
            return
        }
        showErrorMsg(text, status)
        if (refManager.baseQuickAdapter.data.isEmpty()) {
            refManager.multiStateView.showError()
        }
        resetRefreshStatus()
    }
    /*
        错误信息提示，默认实现
     */
    override fun onError(text: String, status:Int) {
        isLoading = false
        if (isDetached) {
            return
        }
        resetRefreshStatus()
        showErrorMsg(text, status)
    }

    /**
     * 网络获取列表数据成功后回调
     */
    override fun onGetListResult(list: MutableList<E>?, page: Page?) {
        if (isDetached) {
            return
        }
        handleResultData(list, page)
    }

    private fun handleResultData(list: MutableList<E>?, page: Page?) {
        refManager.refreshLayout.finishRefresh(true)
        refManager.refreshLayout.finishLoadMore(true)
        refManager.refreshLayout.resetNoMoreData()
        refManager.multiStateView.showContent()

        //初始数据，设置列表
        if (currentPage == 0) {//是刷新请求第一页数据
            refManager.baseQuickAdapter.setNewInstance(list)
        }

        //则显示无数据页面
        if (refManager.baseQuickAdapter.data.isEmpty()) {
            refManager.multiStateView.showEmptyData()
        }

        //无分页列表，设置页面为不可加载状态
        refManager.refreshLayout.setEnableLoadMore(page != null)
        if (page == null) {
            refManager.refreshLayout.finishLoadMoreWithNoMoreData()
            return
        }

        currentPage = page.currentPage

        //加载更多，追加列表
        if (currentPage > 0) {
            if (!list.isNullOrEmpty()) {
                refManager.baseQuickAdapter.addData(list)
            }
        }

        //没有更多数据的时候，设置列表状态为无更多数据
        if (refManager.baseQuickAdapter.data.size >= page.total) {
            refManager.refreshLayout.finishLoadMoreWithNoMoreData()
        }
    }

    /**
     * 下拉刷新回调
     */
    override fun onRefresh(refreshLayout: RefreshLayout) {
        getListData()
    }

    /**
     * 上拉加载更多回调
     */
    override fun onLoadMore(refreshLayout: RefreshLayout) {
        getListData(currentPage)
    }

    /**
     * 获取所需的view控件以及Adapter
     */
    protected abstract fun getRefreshViewManager(): RefreshViewManager<E>
    /**
     * 获取网络请求所需参数
     */
    protected abstract fun getListRequestParams():HashMap<String,Any>

    protected abstract fun getV():V


    open fun showErrorMsg(text: String, status:Int){
        toast(text)
    }

    private fun resetRefreshStatus(){
        if (refManager.refreshLayout.state != RefreshState.RefreshFinish) {
            refManager.refreshLayout.finishRefresh()
        }
        if (refManager.refreshLayout.state != RefreshState.LoadFinish) {
            refManager.refreshLayout.finishLoadMore()
        }
    }
}
