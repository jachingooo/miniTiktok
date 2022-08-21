package com.qxy.miniTiktok.util

import java.net.URL


data class bdResponse(val data:NewData,val extra:NewExtra)

data class NewData(val active_time:String,val description:String,val error_code:Int,
                   val list:List<bd>)

data class NewExtra(val error_code: String,val logid:String,val now:String,val sub_description:String,
                    val sub_error_code:String,val description: String)

data class bd(
    val poster:String,
    val name:String,
    val directors:List<String>,
    val actors:List<String>,
    val release_date:String,
    val areas:List<String>,
    val discussion_hot:String,
    val hot:String,
    val search_hot:String,
    val influence_hot:String,
    val topic_hot:String,
    val type:String,
    val id:String,
    val maoyan_id:String,
    val name_en:String,
    val tags:List<String>,
)