package com.test.miniTiktok.act

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.test.miniTiktok.R
import com.test.miniTiktok.adapter.FragmentAdapter
import com.test.miniTiktok.bean.TabEntity
import com.test.miniTiktok.frag.FsFragment
import com.test.miniTiktok.frag.GzFragment
import kotlinx.android.synthetic.main.act_fans.*
import java.util.ArrayList


/**
 * @description 关注、粉丝Act
 * @time:
 * @author:
 */
class FansAct :AppCompatActivity(){

    private lateinit var tabs: Array<String>
    val mTabEntities = ArrayList<CustomTabEntity>()
    private var fragmentList = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_fans)
        initView()
    }
    private fun initView(){
        tabs = arrayOf("关注", "粉丝")
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

        fragmentList.add(GzFragment())
        fragmentList.add(FsFragment())

        vp_main.adapter = FragmentAdapter(supportFragmentManager, fragmentList)
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

        var isGz=intent.getBooleanExtra("isGz",false)
        if(isGz) {
            vp_main.setCurrentItem(0,false)
        }else {
            vp_main.setCurrentItem(1,false)
        }
    }
}