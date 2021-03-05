package com.xinhuan.wms.baselibrary.data.net

import com.xinhuan.wms.baselibrary.BuildConfig
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.xinhuan.wms.baselibrary.common.BaseConstant
import com.xinhuan.wms.baselibrary.utils.RequestParams
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
  *  Retrofit工厂
 */
class RetrofitFactory private constructor() {


    companion object {
        val instance: RetrofitFactory by lazy { RetrofitFactory() }
        const val POST_TYPE_JSON = "Content-Type: application/json"
    }


    private lateinit var mHeaderInterceptor: Interceptor
    private lateinit var retrofit: Retrofit

    /**
     * 供外部初始化调用
     */
    fun init(headers: HashMap<String, String>?){
        addHeader(headers)
        retrofitInit()
    }

    private fun addHeader(headers: HashMap<String, String>?) {
        //通用拦截
        mHeaderInterceptor = Interceptor { chain ->
            val builder = chain.request().newBuilder()
            //添加header
            headers?.forEach {
                builder.addHeader(it.key, it.value)
            }
            builder.method(chain.request().method, chain.request().body)
            chain.proceed(builder.build())
        }

    }


    private fun retrofitInit() {
        //Retrofit实例化
        retrofit = Retrofit.Builder()
            .baseUrl(BaseConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(initClient())
            .build()
    }

    /**
     * OKHttp创建
     */
    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(mHeaderInterceptor)
            .addInterceptor(initLogInterceptor())//注意 log打印拦截要在添加Header拦截器之后添加，否则打印不全
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    /**
     * 日志拦截器
     */
    private fun initLogInterceptor(): LoggingInterceptor {
        return LoggingInterceptor.Builder()
                //生产环境不打印log，避免了上传图片打印Log出现的OOM
            .setLevel(if (!BuildConfig.IS_ONLINE) Level.BASIC else Level.NONE)
            .log(Platform.INFO)
            .request("Request")
            .response("Response")
            .build()
    }

    /**
     * 具体服务实例化
     */
    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}
