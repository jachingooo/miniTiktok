package com.qxy.miniTiktok.bean

import android.graphics.Bitmap

/**
 * @description 综艺bean
 * @time:
 * @author:
 */
data class ZyBean(
    var url:Bitmap?=null,
    var name:String?=null,
    var nameEn:String?=null,
    var createUser:String?=null,
    var hot1:String?=null,
    var hot2:String?=null,
    var hot3:String?=null,
    var hot4:String?=null
)
