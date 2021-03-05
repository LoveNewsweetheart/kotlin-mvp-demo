package com.xinhuan.wms.demo.provider.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xinhuan.wms.baselibrary.data.protocol.Page
import com.xinhuan.wms.baselibrary.presenter.BaseListPresenter
import com.xinhuan.wms.baselibrary.presenter.view.BaseListView
import com.xinhuan.wms.baselibrary.ui.BaseActivity
import com.xinhuan.wms.baselibrary.utils.ObjUtils
import com.xinhuan.wms.baselibrary.widgets.ProgressLoading
import com.xinhuan.wms.demo.provider.entity.RefreshViewManager
import com.xinhuan.wms.demo.provider.ext.*
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import org.jetbrains.anko.toast

//可处理刷新的Activity
abstract class BaseListMvpActivity<E, P : BaseListPresenter<E, V>, V : BaseListView<E>>  : BaseActivity(), BaseListView<E>,
    OnRefreshListener, OnLoadMoreListener {


    lateinit var mPresenter: P
    private lateinit var mLoadingDialog: ProgressLoading

    private lateinit var refManager: RefreshViewManager<E>
    private var pageNum = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init()

    }

    private fun init(){
        //初始加载框
        mLoadingDialog = ProgressLoading(this)

        bindPresenter()

        refManager = getRefreshViewManager()

        initRecyclerView()
        refManager.refreshLayout.setOnRefreshListener(this)
        refManager.refreshLayout.setOnLoadMoreListener(this)


        refManager.multiStateView.setOnRetryCallback {
            //点击屏幕重试回调（只有在网络请求失败而出现失败页面时）
            getListData()
        }

        if (isInitData()) {
            getFirstList()
        }
    }

    /**
     * 是否进入页面后就自动加载第一页数据
     */
    open fun isInitData(): Boolean {
        return true
    }

    /**
     * 获取第一页列表内容
     */
    protected fun getFirstList() {
        getListData()
    }

    private fun getListData() {
        pageNum = 1
        getListData(pageNum)
    }

    protected fun initRecyclerView(){
        refManager.recyclerView.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        refManager.recyclerView.adapter = refManager.baseQuickAdapter
    }

    private fun bindPresenter() {
        mPresenter = ObjUtils.getObj(this, 1)
        mPresenter.mView = getV()
        mPresenter.lifecycleProvider = this
        mPresenter.context = this
    }

    override fun showLoading() {
        if (isDestroyed) {
            return
        }
        mLoadingDialog.showLoading()
    }

    override fun onShowRefreshUi() {
        if (isDestroyed) {
            return
        }
        if (refManager.baseQuickAdapter.data.isEmpty()) {
            refManager.multiStateView.showLoading()
        }
    }

    override fun onHindRefreshUi() {
        if (isDestroyed) {
            return
        }
        refManager.refreshLayout.finishRefresh()
        refManager.refreshLayout.finishLoadMore()
    }

    /**
     *   隐藏加载框，默认实现
     */
    override fun hideLoading() {
        if (isDestroyed) {
            return
        }
        mLoadingDialog.hideLoading()
    }

    override fun onListError(text: String, status: Int) {
        showErrorMsg(text, status)
        if (refManager.baseQuickAdapter.data.size == 0) {
            refManager.multiStateView.showError()
        }
        resetRefreshStatus()
    }

    /**
     *  错误信息提示，默认实现
     */
    override fun onError(text: String, status:Int) {
        showErrorMsg(text, status)
        resetRefreshStatus()
    }


    private fun getListData(pageNum: Int) {
        val params = getListRequestParams()
        params["currentPage"] = pageNum
        params["pageSize"] = "10"
        mPresenter.getListData(params)
    }


    override fun onGetListResult(list: MutableList<E>?, page: Page?) {
        if (isFinishing) {
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
        if (pageNum == 1) {
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

        pageNum = page.currentPage

        //加载更多，追加列表
        if (pageNum > 1) {
            if (!list.isNullOrEmpty()) {
                refManager.baseQuickAdapter.addData(list)
            }
        }

        //没有更多数据的时候，设置列表状态为无更多数据
        if (refManager.baseQuickAdapter.data.size >= page.total) {
            refManager.refreshLayout.finishLoadMoreWithNoMoreData()
        }
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        getListData()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        pageNum++
        getListData(pageNum)
    }


    override fun onDestroy() {
        super.onDestroy()
        mLoadingDialog.hideLoading()
    }
    /**
     * 获取所需的view控件以及Adapter
     */
    protected abstract fun getRefreshViewManager(): RefreshViewManager<E>
    /**
     * 获取网络请求所需参数
     */
    protected abstract fun getListRequestParams():HashMap<String,Any>

    @LayoutRes
    protected abstract fun getLayoutId():Int

    protected abstract fun getV():V



    protected fun showErrorMsg(text: String, status:Int){
        toast(text)
    }

    /**
     * 恢复刷新框架状态
     */
    private fun resetRefreshStatus(){
        if (refManager.refreshLayout.state != RefreshState.RefreshFinish) {
            refManager.refreshLayout.finishRefresh()
        }
        if (refManager.refreshLayout.state != RefreshState.LoadFinish) {
            refManager.refreshLayout.finishLoadMore()
        }
    }
}
