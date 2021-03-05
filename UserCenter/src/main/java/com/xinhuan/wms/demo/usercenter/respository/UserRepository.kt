package com.xinhuan.wms.demo.usercenter.respository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xinhuan.wms.baselibrary.data.protocol.BaseResp
import com.xinhuan.wms.baselibrary.data.net.RetrofitFactory
import com.xinhuan.wms.demo.usercenter.api.UserApi
import com.xinhuan.wms.demo.usercenter.entity.*
import io.reactivex.Observable
import kotlin.collections.HashMap


/*
    用户相关数据层
    做网络请求
 */
class UserRepository {


    //修改密码
    fun modifyPwd(params: HashMap<String, Any>): Observable<BaseResp<Any>> {
       // return RetrofitFactory.instance.create(UserApi::class.java).modifyPwd(params)
        val str = "{\n" +
                "\t\"status\": 0,\n" +
                "\t\"msg\": \"成功\",\n" +
                "\t\"content\": {\n" +
                "\t}\n" +
                "}"
        val data = Gson().fromJson<BaseResp<Any>>(str, object : TypeToken<BaseResp<Any>>() {}.type)
        return Observable.just(data)
    }

    //用户登录
    fun userLogin(params: HashMap<String, Any>): Observable<BaseResp<UserInfoEntity>> {
        //TODO 实际开发将下方代码打开
        //return RetrofitFactory.instance.create(UserApi::class.java).userLogin(params)
        //TODO 实际开发将下方代码关闭
        //TODO  这里模拟登录成功
        val str = "{\n" +
                "\t\"status\": 0,\n" +
                "\t\"ecode\": 0,\n" +
                "\t\"msg\": \"成功\",\n" +
                "\t\"content\": {\n" +
                "\t\t\"userName\": \"xinhuan\",\n" +
                "\t\t\"userId\": 277638,\n" +
                "\t\t\"userAge\": 30,\n" +
                "        \"userAccount\":\"abcdefg\"\n" +
                "\t}\n" +
                "}"
        val data = Gson().fromJson<BaseResp<UserInfoEntity>>(str, object : TypeToken<BaseResp<UserInfoEntity>>() {}.type)
        return Observable.just(data)
    }


}
