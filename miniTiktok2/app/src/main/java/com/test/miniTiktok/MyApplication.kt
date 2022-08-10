package com.test.miniTiktok

import android.app.Application
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
    }
}