package com.qxy.miniTiktok.bean

/**
 * @description 关注接口回复bean
 * @time:
 * @author:
 */
data class gzResBean (

    var extra: extrabin,
    var data: databin
)

data class extrabin (
    var error_code: Int,
    var description: String,
    var sub_error_code: Int,
    var sub_description: String,
    var logid: String,
    var now: Long
    )

data class databin (
    var error_code: Int,
    var description: String,
    var cursor: Int,
    var has_more: Boolean,
    var list: List<data_listbin>
        )

data class data_listbin (
    var open_id:String,
    var union_id:String,
    var nickname:String,
    var avatar:String,
    var city:String,
    var province:String,
    var country:String,
    var gender:Int
        )