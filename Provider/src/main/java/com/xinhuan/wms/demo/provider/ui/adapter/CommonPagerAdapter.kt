package com.xinhuan.wms.demo.provider.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.xinhuan.wms.demo.provider.entity.PagerFragmentBean

class CommonPagerAdapter(private val fragments:List<PagerFragmentBean>, manager:FragmentManager) : FragmentPagerAdapter(manager){


    override fun getItem(position: Int): Fragment {
        return fragments[position].fragment
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragments[position].tabText
    }

}