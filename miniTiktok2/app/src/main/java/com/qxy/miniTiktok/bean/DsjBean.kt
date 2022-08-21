package com.qxy.miniTiktok.bean

import android.graphics.Bitmap

/**
 * @description 电视剧对象bean
 * @time:
 * @author:
 */
data class DsjBean(
    var url:Bitmap?=null,
    var name:String?=null,
    var nameEn:String?=null,
    var type:String?=null,
    var createUser:String?=null,
    var time:String?=null,
    var area:String?=null,
    var hot1:String?=null,
    var hot2:String?=null,
    var hot3:String?=null,
    var hot4:String?=null
)
