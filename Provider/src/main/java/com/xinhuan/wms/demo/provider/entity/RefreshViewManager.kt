package com.xinhuan.wms.demo.provider.entity

import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kennyc.view.MultiStateView
import com.scwang.smartrefresh.layout.api.RefreshLayout

class RefreshViewManager<E>(
    val recyclerView:RecyclerView,
    val refreshLayout: RefreshLayout,
    val multiStateView: MultiStateView,
    val baseQuickAdapter: BaseQuickAdapter<E, *>
)