package com.test.miniTiktok.act

import android.os.Bundle
import android.text.TextUtils
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.test.miniTiktok.R
import com.test.miniTiktok.frag.DsjFragment
import com.test.miniTiktok.frag.DyFragment
import com.test.miniTiktok.frag.MyFragment
import com.test.miniTiktok.frag.ZyFragment
import kotlinx.android.synthetic.main.act_newmain.*


/**
 * @description 主页-含底部四个菜单
 * @time:
 * @author:
 */
class NewMainAct :AppCompatActivity(){

    private val TAG_DY = "tag_dy"
    private val TAG_DSJ = "tag_dsj"
    private val TAG_ZY = "tag_zy"
    private val TAG_WD = "tag_wd"
    private val mFragmentList= arrayListOf<Fragment>()
    private val fragment1: Fragment = DyFragment()
    private val fragment2: Fragment = DsjFragment()
    private val fragment3: Fragment = ZyFragment()
    private val fragment4: Fragment = MyFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_newmain)
        initView()
    }

    private fun initView() {
        //默认首页
        rg_type.check(R.id.rb_one)
        changeFragment(TAG_DY)
        rg_type.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            if (R.id.rb_one === checkedId) {
                changeFragment(TAG_DY)
            } else if (R.id.rb_two === checkedId) {
                changeFragment(TAG_DSJ)
            }else if (R.id.rb_three === checkedId) {
                changeFragment(TAG_ZY)
            } else if (R.id.rb_four === checkedId) {
                changeFragment(TAG_WD)
            }
        })
    }

    private fun changeFragment(tag: String) {
        hideFragment()
        val transaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment != null) {
            transaction.show(fragment)
        } else {
            if (TextUtils.equals(TAG_DY, tag)) {
                fragment = fragment1
            } else if (TextUtils.equals(TAG_DSJ, tag)) {
                fragment = fragment2
            }else if (TextUtils.equals(TAG_ZY, tag)) {
                fragment = fragment3
            }else if (TextUtils.equals(TAG_WD, tag)) {
                fragment = fragment4
            }
            if (fragment == null) {
                return
            }
            mFragmentList.add(fragment)
            transaction.add(R.id.framelayout_main, fragment, tag)
        }
        transaction.commitAllowingStateLoss()
        supportFragmentManager.executePendingTransactions()
    }

    private fun hideFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        for (i in mFragmentList.indices) {
            transaction.hide(mFragmentList.get(i))
        }
        transaction.commit()
    }
}