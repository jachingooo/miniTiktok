package com.qxy.miniTiktok.bean

import android.provider.ContactsContract

data class MyResBean (
    var data: List<subDataBean>
)

data class subDataBean (
    var avatar: Int,
    var city: String,
    var country: String,
    var description: String,
    var e_account_role: String,
    var errorcode: String,
    var gender: String,
    var nickname: String,
    var open_id: String,
    var province: String,
    var union_id: String
        )