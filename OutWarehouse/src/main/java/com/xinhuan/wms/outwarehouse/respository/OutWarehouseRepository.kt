package com.xinhuan.wms.outwarehouse.respository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xinhuan.wms.baselibrary.data.protocol.BaseResp
import com.xinhuan.wms.outwarehouse.entity.ListTaskEntity
import io.reactivex.Observable


class OutWarehouseRepository {


    fun getTaskList(params: HashMap<String, Any>): Observable<BaseResp<ListTaskEntity>> {

        //TODO 实际开发将下方代码打开 进行从网络获取数据
        //return RetrofitFactory.instance.create(OutWarehouseApi::class.java).getTaskList(params)
        //TODO 实际开发将下方代码关闭
        //TODO  这里模拟数据请求列表成功返回的结果
        val currentPage = if(params.containsKey("currentPage") ) params["currentPage"].toString().toInt() else 0
        val str = "{\n" +
                "\t\"status\": 0,\n" +
                "\t\"msg\": \"成功 \",\n" +
                "\t\"content\": {\n" +
                "\t\t\"page\": {\n" +
                "\t\t\t\"currentPage\": $currentPage,\n" +
                "\t\t\t\"total\": 20\n" +
                "\t\t},\n" +
                "\t\t\"data\": [{\n" +
                "\t\t\t\t\"taskName\": \"散货任务\",\n" +
                "\t\t\t\t\"taskCreateDate\": \"03-04\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"taskName\": \"整货任务\",\n" +
                "\t\t\t\t\"taskCreateDate\": \"03-05\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"taskName\": \"整货任务1\",\n" +
                "\t\t\t\t\"taskCreateDate\": \"03-05\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"taskName\": \"容器拆分\",\n" +
                "\t\t\t\t\"taskCreateDate\": \"03-05\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"taskName\": \"整货任务2\",\n" +
                "\t\t\t\t\"taskCreateDate\": \"03-05\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"taskName\": \"订单取消1\",\n" +
                "\t\t\t\t\"taskCreateDate\": \"03-02\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"taskName\": \"订单取消2\",\n" +
                "\t\t\t\t\"taskCreateDate\": \"03-02\"\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"taskName\": \"订单取消\",\n" +
                "\t\t\t\t\"taskCreateDate\": \"03-02\"\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t}\n" +
                "}"
        val data = Gson().fromJson<BaseResp<ListTaskEntity>>(str, object : TypeToken<BaseResp<ListTaskEntity>>() {}.type)
        return Observable.just(data)
    }

}
