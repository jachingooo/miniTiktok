package com.test.miniTiktok.frag

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.test.miniTiktok.R
import com.test.miniTiktok.bean.ZpBean


/**
 * @description 抖音作品视频页（懒加载，只预加载2页，动态创建-根据视频的个数）
 * @time:
 * @author:
 */
class MovieFragment:Fragment(){

    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    var mHasLoadedOnce=false
    var isInit=false
    var isLoad=false
    var tv_title:TextView?=null

    companion object {
        var TAG_BEAN="tag_bean"
        fun newInstance(bean: ZpBean): MovieFragment {
            val fragment = MovieFragment()
            val args = Bundle()
            args.putParcelable(TAG_BEAN, bean)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.frag_movie, null)
        //处理预加载问题，让fragment懒加载
        isInit = true
        //初始化的时候去加载数据
        isCanLoadData()
        initView(view)
        return view
    }

    private fun initView(v: View) {
        var tv_title = v.findViewById<TextView>(R.id.tv_title)
        var tv_time = v.findViewById<TextView>(R.id.tv_time)
        var tv_sj1 = v.findViewById<TextView>(R.id.tv_sj1)
        var tv_sj2 = v.findViewById<TextView>(R.id.tv_sj2)
        var tv_sj3 = v.findViewById<TextView>(R.id.tv_sj3)
        var tv_sj4 = v.findViewById<TextView>(R.id.tv_sj4)
        var tv_sj5 = v.findViewById<TextView>(R.id.tv_sj5)
        val extras = arguments
        extras?.let {
            var bean= it.getParcelable<ZpBean>(TAG_BEAN)
            if(null!=bean) {
                if (!TextUtils.isEmpty(bean?.title))
                    tv_title.text = bean.title
                if(!TextUtils.isEmpty(bean?.time))
                    tv_time.text=bean.time
                tv_sj1.text=""+bean.sj1
                tv_sj2.text=""+bean.sj2
                tv_sj3.text=""+bean.sj3
                tv_sj4.text=""+bean.sj4
                tv_sj5.text=""+bean.sj5
            }
        }
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        isCanLoadData()
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private fun isCanLoadData() {
        //视图没有初始化
        if (!isInit) {
            return
        }
        //判断视图对用户是否可见
        if (userVisibleHint) {
            lazyLoad()
            isLoad = true
        } else {
            if (isLoad) {
                stopLoad()
            }
        }
    }
    /**
     * 当视图初始化并对用户可见的时候去真正的加载数据
     */
    private fun lazyLoad() {
        if (mHasLoadedOnce)
            return
        mHasLoadedOnce=true
    }
    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以覆写此方法
     */
    private fun stopLoad() {
        //让已经在加载过数据并不可见的页面停止加载（例如 视频播放时切换过去不可见时，要让它停止播放）
    }
}