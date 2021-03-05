package com.xinhuan.wms.demo.usercenter.service

import com.xinhuan.wms.baselibrary.utils.convert
import com.xinhuan.wms.baselibrary.utils.convertBoolean
import com.xinhuan.wms.demo.usercenter.entity.*
import com.xinhuan.wms.demo.usercenter.respository.UserRepository
import io.reactivex.Observable


class UserService {

    fun userLogin(params: HashMap<String, Any>): Observable<UserInfoEntity> {
        val repository = UserRepository()
        //convert 转成你要的对象UserInfoEntity convertBoolean返回的是布尔类型
        return repository.userLogin(params).convert()

    }


    fun modifyPwd(params: HashMap<String, Any>): Observable<Boolean> {
        val repository = UserRepository()
        return repository.modifyPwd(params).convertBoolean()
    }


}