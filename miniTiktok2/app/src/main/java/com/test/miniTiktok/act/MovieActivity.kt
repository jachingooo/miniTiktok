package com.test.miniTiktok.act

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.test.miniTiktok.R
import com.test.miniTiktok.bean.ZpBean
import com.test.miniTiktok.frag.MovieFragment
import com.test.miniTiktok.util.TestDataBean
import kotlinx.android.synthetic.main.act_movie.*


/**
 * @description 视频页容器（根据视频点击的位置和数据，动态生成视频页Fragment）
 * @time:
 * @author:
 */
class MovieActivity :AppCompatActivity(){

    private var mAdapter: DetailPagerAdapter? = null
    private var mFragmentList=ArrayList<Fragment>()
    private var mPosition=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_movie)
        mPosition=intent.getIntExtra("position",-1)
        initView()
    }
    private fun initView(){
        mAdapter = DetailPagerAdapter(supportFragmentManager)
        vp_detail.setAdapter(mAdapter)
        vp_detail.addOnPageChangeListener(OnFragmentPageChangeListener())
        initData()
        vp_detail.setCurrentItem(mPosition)
    }

    private fun initData(){
        mAdapter?.setmList(TestDataBean.getMovieData())
        mAdapter?.notifyDataSetChanged()

    }

    private inner class DetailPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        internal var list: List<ZpBean>? = null

        fun setmList(mList: List<ZpBean>) {
            this.list = mList
        }

        override fun getItem(position: Int): Fragment {
            Log.d("getItemgetItem","getItem:"+position)
            var fragment= MovieFragment.newInstance(list!!.get(position))
            mFragmentList.add(fragment)
            return fragment
        }

        override fun getCount(): Int {
            return if (list == null) 0 else list!!.size
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        }
    }

    private inner class OnFragmentPageChangeListener : ViewPager.OnPageChangeListener {

        override fun onPageScrolled(position: Int, offsetPerc: Float,
                                    offsetPixel: Int) {
        }

        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageSelected(position: Int) {
            mPosition=position
        }
    }
}