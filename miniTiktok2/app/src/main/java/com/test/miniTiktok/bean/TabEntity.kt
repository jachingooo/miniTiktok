package com.test.miniTiktok.bean

import com.flyco.tablayout.listener.CustomTabEntity


/**
 * @description Tab头部菜单bean
 * @time:
 * @author:
 */
class TabEntity: CustomTabEntity {
    var title: String? = null
    var selectedIcon = 0
    var unSelectedIcon = 0
    constructor(title: String?, selectedIcon: Int, unSelectedIcon: Int) {
        this.title = title
        this.selectedIcon = selectedIcon
        this.unSelectedIcon = unSelectedIcon
    }
    override fun getTabTitle(): String {
        return title.toString()
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }
}