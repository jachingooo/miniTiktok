package com.qxy.miniTiktok.bean

/**
 * @description 关注接口回复bean
 * @time:
 * @author:
 */
data class FsResBean (
    var extra: extrabin,
    var data: databinF
)

data class databinF (
    var total: Int,
    var error_code: Int,
    var description: String,
    var cursor: Int,
    var has_more: Boolean,
    var list: List<data_listbin>
)
