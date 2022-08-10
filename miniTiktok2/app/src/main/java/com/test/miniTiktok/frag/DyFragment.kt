package com.test.miniTiktok.frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.flyco.tablayout.CommonTabLayout
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.test.miniTiktok.R
import com.test.miniTiktok.adapter.FragmentAdapter
import com.test.miniTiktok.bean.TabEntity
import java.util.*

/**
 * @description 电视页面Fragment-含子fragment（院线+网络电影fragment）
 * @time:
 * @author:
 */
class DyFragment: Fragment(){

    private lateinit var tabs: Array<String>
    val mTabEntities = ArrayList<CustomTabEntity>()
    private var fragmentList = mutableListOf<Fragment>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.frag_dy, null)
        initView(view)
        return view
    }
    private fun initView(v: View){
        var vp_main=v.findViewById<ViewPager>(R.id.vp_main)
        var tab_type=v.findViewById<CommonTabLayout>(R.id.tab_type)
        tabs = arrayOf("院线电影", "网络电影")
        for (i in tabs.indices) {
            mTabEntities.add(TabEntity(tabs[i], 0, 0))
        }
        tab_type.setTabData(mTabEntities)
        tab_type.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                vp_main.currentItem = position
            }

            override fun onTabReselect(position: Int) {
            }
        })

        fragmentList.add(YxFrag())
        fragmentList.add(WlFrag())

        vp_main.adapter = FragmentAdapter(childFragmentManager, fragmentList)
        vp_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                tab_type.currentTab = position
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
        vp_main.offscreenPageLimit = 2
    }
}