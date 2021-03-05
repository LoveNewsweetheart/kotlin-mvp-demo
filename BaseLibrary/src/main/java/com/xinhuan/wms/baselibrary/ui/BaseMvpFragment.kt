package com.xinhuan.wms.baselibrary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xinhuan.wms.baselibrary.presenter.BasePresenter
import com.xinhuan.wms.baselibrary.presenter.view.BaseView
import com.xinhuan.wms.baselibrary.utils.ObjUtils
import com.xinhuan.wms.baselibrary.widgets.ProgressLoading
import org.jetbrains.anko.toast

/*
    Fragment基类，业务相关
 */
abstract class BaseMvpFragment<P : BasePresenter<V>, V : BaseView> : BaseFragment(), BaseView {

    private lateinit var mLoadingDialog: ProgressLoading
    lateinit var mPresenter: P
    var  isLoading = false//是否正处于网络加载中

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mPresenter = ObjUtils.getObj(this, 0)
        mPresenter.mView = getV()
        mPresenter.lifecycleProvider = this
        mPresenter.context = activity!!

        //初始加载框
        mLoadingDialog = ProgressLoading(activity!!)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    protected abstract fun getV():V

    /*
       显示加载框，默认实现
    */
    override fun showLoading() {
        mLoadingDialog.showLoading()
        isLoading = true
    }

    /*
        隐藏加载框，默认实现
     */
    override fun hideLoading() {
        mLoadingDialog.hideLoading()
        isLoading = false
    }

    /*
        错误信息提示，默认实现
     */
    override fun onError(text:String, status:Int) {
        showErrorMsg(text,status)
        isLoading = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mLoadingDialog.hideLoading()
    }

    protected fun showErrorMsg(text: String, status:Int){
        activity?.toast(text)
    }
}
