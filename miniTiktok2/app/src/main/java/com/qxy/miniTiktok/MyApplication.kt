package com.qxy.miniTiktok

import android.app.Application
import android.content.Context
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory
import com.bytedance.sdk.open.douyin.DouYinOpenConfig
import com.lzy.okgo.OkGo


/**
 * @description Application基类-初始化网络请求框架等
 * @time:
 * @author:
 */
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        //初始化OkGo
        OkGo.getInstance().init(this)

        val clientkey = "awvayupkp25mxr6u" // 需要到开发者网站申请

        DouYinOpenApiFactory.init(DouYinOpenConfig(clientkey))
    }

}