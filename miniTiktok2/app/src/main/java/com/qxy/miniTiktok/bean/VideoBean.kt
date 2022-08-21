package com.qxy.miniTiktok.bean

import com.qxy.miniTiktok.util.NewExtra
import java.sql.Time

data class VideoBean(
    var data:VideoData,
    var extra: NewExtra
)

data class VideoData(
    var list:List<Video>,
    var error_code: Int,
    var description:String,
    var cursor: String,
    var has_more:Boolean
)

data class Video(
    var item_id:String,
    var media_type:Int,
    var share_url:String,
    var statistics:NewSta,
    var title:String,
    var video_id:String,
    var create_time:Long,
    var is_top:Boolean,
    var video_status: Int,
    var cover:String,
    var is_reviewed:Boolean
)

data class NewSta(
    var digg_count:Int,
    var download_count:Int,
    var forward_count:Int,
    var play_count:Int,
    var share_count:Int,
    var comment_count:Int
)