package com.xinhuan.wms.baselibrary.ui

import android.os.Bundle
import com.xinhuan.wms.baselibrary.presenter.BasePresenter
import com.xinhuan.wms.baselibrary.presenter.view.BaseView
import com.xinhuan.wms.baselibrary.utils.ObjUtils
import com.xinhuan.wms.baselibrary.widgets.ProgressLoading
import org.jetbrains.anko.toast

/*
    Activity基类，业务相关
 */
abstract class BaseMvpActivity<P : BasePresenter<V>, V : BaseView> : BaseActivity(), BaseView {

    lateinit var mPresenter: P
    private lateinit var mLoadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        takeData()
        super.onCreate(savedInstanceState)

        mPresenter = ObjUtils.getObj(this, 0)
        mPresenter.mView = getV()
        mPresenter.lifecycleProvider = this
        mPresenter.context = this

        //初始加载框
        mLoadingDialog = ProgressLoading(this)
    }

    //获取上一个页面传过来的值
    open fun takeData(){}

    protected abstract fun getV():V


    /*
        显示加载框，默认实现
     */
    override fun showLoading() {
        mLoadingDialog.showLoading()
    }

    /*
        隐藏加载框，默认实现
     */
    override fun hideLoading() {
        mLoadingDialog.hideLoading()
    }

    /*
        错误信息提示，默认实现
     */
    override fun onError(text: String, status:Int) {
        showErrorMsg(text, status)
    }

    open fun showErrorMsg(text: String, status:Int){
        toast(text)
    }

    override fun onDestroy() {
        super.onDestroy()
        mLoadingDialog.hideLoading()
    }

}
