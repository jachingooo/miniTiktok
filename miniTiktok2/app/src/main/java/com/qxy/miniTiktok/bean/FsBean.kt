package com.qxy.miniTiktok.bean

import android.graphics.Bitmap


/**
 * @description 关注、粉丝bean
 * @time:
 * @author:
 */
data class FsBean(
    var url:Bitmap?=null,
    var name:String?=null,
    var xb:String?=null,
    var city:String?=null
)