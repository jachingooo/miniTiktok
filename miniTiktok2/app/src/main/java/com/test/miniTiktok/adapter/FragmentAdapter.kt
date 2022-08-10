package com.test.miniTiktok.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.FragmentTransaction
import java.util.ArrayList

/**
 * @description 头部菜单Tab+viewpager联动适配器
 * @time:
 * @author:
 */
class FragmentAdapter (fm: FragmentManager, fragmentList: List<Fragment>? ) : FragmentStatePagerAdapter(fm) {
    var fragmentList: List<Fragment>? = ArrayList()
    var currentFragment: Fragment? = null
        private set

    init {
        setFragments(fm, fragmentList)
    }

    //刷新fragment
    fun setFragments(fm: FragmentManager, fragments: List<Fragment>? ) {
        if (fragmentList != null) {
            var ft: FragmentTransaction? = fm.beginTransaction()
            for (f in fragmentList!!) {
                ft!!.remove(f)
            }
            ft!!.commitAllowingStateLoss()
            ft = null
            fm.executePendingTransactions()
        }
        fragmentList = fragments
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return  ""
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList!![position]
    }

    override fun getCount(): Int {
        return fragmentList!!.size
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        try {
            currentFragment = `object` as Fragment
            super.setPrimaryItem(container, position, `object`)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}