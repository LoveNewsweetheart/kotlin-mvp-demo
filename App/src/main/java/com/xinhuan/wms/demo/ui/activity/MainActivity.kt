package com.xinhuan.wms.demo.ui.activity

import android.os.Bundle
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.xinhuan.wms.baselibrary.ui.BaseActivity
import com.xinhuan.wms.demo.R
import com.xinhuan.wms.demo.provider.router.RouterPath
import com.xinhuan.wms.demo.ui.fragment.PlatformFragment
import com.xinhuan.wms.outwarehouse.ui.fragment.TaskListFragment
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = RouterPath.App.MAIN_ACTIVITY)
class MainActivity : BaseActivity(), RadioGroup.OnCheckedChangeListener {


    private var lastShowFragment: Fragment? = null
    private lateinit var taskFragment: Fragment
    private lateinit var platformFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        taskFragment = TaskListFragment()
        platformFragment = PlatformFragment()

        initView()
    }


    private fun initView() {
        mRg.setOnCheckedChangeListener(this)
        mRbTaskList.isChecked = true
    }

    private fun switchFragment(fragment: Fragment) {

        val mT = supportFragmentManager.beginTransaction()
        if (lastShowFragment != null && lastShowFragment!!.isVisible) {
            mT.hide(lastShowFragment!!)
        }
        if (!fragment.isAdded) {
            mT.add(R.id.mFlContainer, fragment)
        }
        mT.show(fragment)
        mT.commit()
        lastShowFragment = fragment
    }


    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when(checkedId){
            mRbTaskList.id ->{
                switchFragment(taskFragment)
            }
            mRbTaskPlatform.id ->{
                switchFragment(platformFragment)
            }
        }
    }


}