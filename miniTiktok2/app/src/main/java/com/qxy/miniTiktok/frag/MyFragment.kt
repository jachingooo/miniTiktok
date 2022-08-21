package com.qxy.miniTiktok.frag

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.qxy.miniTiktok.R
import com.qxy.miniTiktok.act.FansAct
import com.qxy.miniTiktok.act.MovieActivity
import com.qxy.miniTiktok.adapter.BaseAdapter
import com.qxy.miniTiktok.adapter.ZpAdapter
import com.qxy.miniTiktok.bean.*
import com.qxy.miniTiktok.util.CommonDecoration
import com.qxy.miniTiktok.util.TestDataBean
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

/**
 * @description 我的页面Fragment
 * @time:
 * @author:
 */
class MyFragment :Fragment(){

    private var adapter: ZpAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.frag_my, null)
        initView(view)
        return view
    }
    private fun initView(v: View){
        var tv_gz=v.findViewById<TextView>(R.id.tv_gz)
        var tv_fs=v.findViewById<TextView>(R.id.tv_fs)
        var rv_main=v.findViewById<RecyclerView>(R.id.rv_main)
        rv_main.layoutManager=GridLayoutManager(requireActivity(), 3)
        rv_main.addItemDecoration(CommonDecoration(requireActivity()))
        adapter = ZpAdapter(requireActivity())
        rv_main.adapter = adapter
        initData()

        tv_gz.setOnClickListener {
            val intent=Intent(requireActivity(),FansAct::class.java)
            intent.putExtra("isGz",true)
            startActivity(intent)
        }
        tv_fs.setOnClickListener {
            val intent=Intent(requireActivity(),FansAct::class.java)
            intent.putExtra("isGz",false)
            startActivity(intent)
        }

        adapter?.setItemClickListener(object : BaseAdapter.OnItemClickListener<ZpBean> {
            override fun onItemClick(view: View, data: ZpBean, position: Int) {
                val intent=Intent(requireActivity(),MovieActivity::class.java)
                intent.putExtra("position",position)
                startActivity(intent)
            }

            override fun onItemLongClick(view: View, data: ZpBean, position: Int) {
            }
        })
    }

    /**
     * 测试数据
     */
    private fun initData(){
        getServerData()
    }

    private val url = "https://open.douyin.com/video/list"


    private fun getServerData() {
        val TAG = "getSeverData"
        OkGo.get<String>(url)
            .tag(this)
            .params("count", 10)
            .params("open_id", "_000ZjFPfB26u0nnppodCXU4uKrWTtYogwlf")
            .headers("access-token","act.689358472bd7c60133ef7fb1e925c9e3QKkZbuOeuLUKuo4q6dBstsshdw8y")
            .execute(object : StringCallback() {
                @SuppressLint("WrongConstant") //Response<T> 写对应的后台返回的bean
                override fun onSuccess(response: Response<String>) {
                    val jsonRs = response.body().toString()
                    Log.d(TAG, "onSuccess: "+jsonRs)
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
                            adapter?.clear()
                            adapter?.appendToList(FsList)
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