package com.test.miniTiktok.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * @description 抖音作品对象bean
 * @time:
 * @author:
 */
@Parcelize
data class ZpBean(
    var isTop:Boolean?=null,
    var url:Int?=null,
    var title:String?=null,
    var time:String?=null,
    var playcount:Int?=null,
    var plcount:Int?=null,
    var sj1:Int?=null,
    var sj2:Int?=null,
    var sj3:Int?=null,
    var sj4:Int?=null,
    var sj5:Int?=null
): Parcelable
