package com.xinhuan.wms.demo.provider.utils




/**
 *   封装 提供对外访问的SP管理类
 */
object SpManager {


    fun setToken(token: String) {
        SpUtils.putString("token", token)
    }

    fun getToken(): String {
        return SpUtils.getString("token")
    }

    fun setUserAccount(userAccount: String) {
        SpUtils.putString("userAccount", userAccount)
    }

    fun getUserAccount(): String {
        return SpUtils.getString("userAccount")
    }

    fun setWarehouseName(wareHouseName: String) {
        SpUtils.putString("wareHouseName", wareHouseName)
    }

    fun getWareHouseName(): String {
        return SpUtils.getString("wareHouseName")
    }

    fun setUserName(userName: String) {
        SpUtils.putString("userName", userName)
    }

    fun getUserName(): String {
        return SpUtils.getString("userName")
    }


    fun setUserId(userId: String) {
        SpUtils.putString("userId", userId)
    }

    fun getUserId(): String {
        return SpUtils.getString("userId")
    }

    fun setCarType(carType: Int) {
        SpUtils.putInt("carType", carType)
    }

    fun getCarType(): Int {
        return SpUtils.getInt("carType")
    }



    fun setWarehouseId(warehouseId: String) {
        SpUtils.putString("warehouseId", warehouseId)
    }

    //仓库id
    fun getWarehouseId(): String {
        return SpUtils.getString("warehouseId")
    }




    fun logout() {
        setToken("")
        setUserId("")
        setUserName("")
    }

}