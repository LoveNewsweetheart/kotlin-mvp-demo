package com.xinhuan.wms.demo.provider.utils

import android.graphics.Outline
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import com.xinhuan.wms.demo.provider.R
import org.jetbrains.anko.find

fun setTabUi(tabTextList: MutableList<String>, tabLayout: TabLayout) {

    if (tabTextList.isEmpty() || tabTextList.size <= 1) {
        return
    }

    tabTextList.forEachIndexed { index, s ->
        updateTabStyle(tabLayout.getTabAt(index)!!,s,  tabLayout)
    }
}

private fun updateTabStyle(tab: TabLayout.Tab, tabText:String,  tabLayout: TabLayout) {
    tab.setCustomView(R.layout.item_tab)
    val mTvTab = tab.customView!!.find<TextView>(R.id.mTvTab)
    mTvTab.text = tabText
    //设置背景样式
    val mTabRootView = tab.customView!!.find<ViewGroup>(R.id.mTabRootView)
    if (tab.position == 0) {
        mTabRootView.setBackgroundResource(R.drawable.selector_tab_left_bg)
    } else if (tab.position == tabLayout.tabCount - 1) {
        mTabRootView.setBackgroundResource(R.drawable.selector_tab_right_bg)
    }
}

//设置背景阴影以及圆角
fun setElevationAndRadius(view:View, elevation:Float, radius:Float){
    view.elevation = elevation
    view.outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            outline.setRoundRect(0, 0, view.width, view.height, radius)
        }
    }
}


















