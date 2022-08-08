package com.test.miniTiktok

import android.app.Application
import com.lzy.okgo.OkGo


class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        //初始化OkGo
        OkGo.getInstance().init(this)
    }
}