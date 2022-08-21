package com.qxy.miniTiktok.act

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.qxy.miniTiktok.R
import com.qxy.miniTiktok.bean.VideoBean
import com.qxy.miniTiktok.bean.ZpBean
import com.qxy.miniTiktok.frag.MovieFragment
import com.qxy.miniTiktok.frag.MyFragment
import com.qxy.miniTiktok.util.TestDataBean
import kotlinx.android.synthetic.main.act_movie.*
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread


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
        getServerData()

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
    private val url = "https://open.douyin.com/video/list"


    private fun getServerData() {

        OkGo.get<String>(url)
            .tag(this)
            .params("count", 10)
            .params("open_id", "_000ZjFPfB26u0nnppodCXU4uKrWTtYogwlf")
            .headers("access-token","act.689358472bd7c60133ef7fb1e925c9e3QKkZbuOeuLUKuo4q6dBstsshdw8y")
            .execute(object : StringCallback() {
                @SuppressLint("WrongConstant") //Response<T> 写对应的后台返回的bean
                override fun onSuccess(response: Response<String>) {
                    val jsonRs = response.body().toString()

                    val res = Gson().fromJson(jsonRs, VideoBean::class.java)
                    if (response.code() == 200) {
                        if (!TextUtils.isEmpty(response.body())) {
                            val Fsdata = res.data.list
                            val FsList = ArrayList<ZpBean>()
                            val t1 = thread {
                                for (i in Fsdata) {

                                    val Fs = ZpBean(
                                        i.is_top,
                                        URL(i.share_url),
                                        i.title,
                                        i.create_time.toDateStr(),
                                        i.statistics.play_count,
                                        i.statistics.comment_count,
                                        i.statistics.digg_count,
                                        i.statistics.comment_count,
                                        i.statistics.download_count,
                                        i.statistics.forward_count,
                                        i.statistics.share_count
                                    )
                                    FsList.add(Fs)
                                }
                            }
                            t1.join()
                            mAdapter?.setmList(FsList)
                            mAdapter?.notifyDataSetChanged()
                            //解析服务器返回的数据


                        }
                    }
                }
            })
    }

    @SuppressLint("SimpleDateFormat")
    fun Long.toDateStr(pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
        val date = Date(this)
        val format = SimpleDateFormat(pattern)
        return format.format(date)
    }
}