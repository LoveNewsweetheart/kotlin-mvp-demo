package com.xinhuan.wms.baselibrary.common

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/*
    AMS系统已经维护了栈结构，但是我们无法直接操作。因此自己实现一个来管理
    Activity管理器
 */
class AppManager private constructor(){

    private val activityStack:Stack<Activity> = Stack()

    companion object {
        val instance:AppManager by lazy { AppManager() }
    }

    /*
        Activity入栈
     */
    fun addActivity(activity: Activity){
        activityStack.add(activity)
    }

    /*
        Activity出栈
     */
    fun finishActivity(activity: Activity){
        activity.finish()
        activityStack.remove(activity)
    }

    /*
        获取当前栈顶
     */
    fun currentActivity(): Activity {
        return activityStack.lastElement()
    }

    /**
     * 获取前一个Activity
     */
    fun getPreviousActivity(): Activity {
        return activityStack[activityStack.size - 2]
    }

    /*
        清理栈
     */
    fun finishAllActivity() {
        for (activity in activityStack) {
            activity.finish()
        }
        activityStack.clear()
    }

    /*
        退出应用程序
     */
    fun exitApp(context:Context){
        finishAllActivity()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }
}
