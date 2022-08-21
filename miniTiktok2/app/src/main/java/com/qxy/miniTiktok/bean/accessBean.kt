package com.qxy.miniTiktok.bean

data class accessBean (
    var data: adataBin,
    var message: String
        )
data class adataBin(
    var access_token: String,
    var captcha: String,
    var desc_url: String,
    var description: String,
    var error_code: Int,
    var expires_in: Long,
    var log_id: String,
    var open_id: String,
    var refresh_expires_in: Long,
    var refresh_token: String,
    var scope: String
)