package com.xinhuan.wms.demo.usercenter.api

import com.xinhuan.wms.baselibrary.data.protocol.BaseResp
import com.xinhuan.wms.demo.usercenter.entity.UserInfoEntity
import io.reactivex.Observable
import retrofit2.http.*


/*
    用户相关 接口
 */
interface UserApi {

    /**
     * 注意前面加/和前面不加/的区别
     */
    @GET("/aa/bb/ccc")
    fun userLogin(@QueryMap params: HashMap<String, Any>): @JvmSuppressWildcards Observable<BaseResp<UserInfoEntity>>


    @POST("wms/user/loginRegister")
    fun signInOrOut(@Body params: HashMap<String, Any>): @JvmSuppressWildcards Observable<BaseResp<Any>>

    @POST("/mmm/v3/user/xPassword")
    fun modifyPwd(@Body params: HashMap<String, Any>): @JvmSuppressWildcards Observable<BaseResp<Any>>


    //获取手机验证码
    @GET("/rms/v3/user/sendAuthCode")
    fun sendCheckCode(@QueryMap params: HashMap<String, Any>): @JvmSuppressWildcards Observable<BaseResp<Any>>


//    /*
//        用户登录
//     */
//    @POST("userCenter/login")
//    fun login(@Body req:LoginReq):Observable<BaseResp<UserInfoEntity>>
//
//    /*
//        忘记密码
//     */
//    @POST("userCenter/forgetPwd")
//    fun forgetPwd(@Body req:ForgetPwdReq):Observable<BaseResp<String>>
//
//    /*
//        重置密码
//     */
//    @POST("userCenter/resetPwd")
//    fun resetPwd(@Body req:ResetPwdReq):Observable<BaseResp<String>>
//
//    /*
//        编辑用户资料
//     */
//    @POST("userCenter/editUser")
//    fun editUser(@Body req:EditUserReq):Observable<BaseResp<UserInfoEntity>>
}
