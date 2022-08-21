package com.qxy.miniTiktok.frag

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.qxy.miniTiktok.PopWindowsUtil
import com.qxy.miniTiktok.R
import com.qxy.miniTiktok.adapter.ZyAdapter
import com.qxy.miniTiktok.bean.DsjBean
import com.qxy.miniTiktok.bean.ZyBean
import com.qxy.miniTiktok.util.TestDataBean
import com.qxy.miniTiktok.util.bdResponse
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

/**
 * @description 综艺页面Fragment
 * @time:
 * @author:
 */
class ZyFragment : Fragment(){
    private var adapter: ZyAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.frag_zy, null)
        initView(view)
        return view
    }
    private fun initView(v: View){
        var rv_main=v.findViewById<RecyclerView>(R.id.rv_main)
        var iv_sxzy=v.findViewById<ImageView>(R.id.iv_sxzy)
        adapter = ZyAdapter(requireActivity())
        rv_main.adapter = adapter
        iv_sxzy.setOnClickListener {
            PopWindowsUtil.showPop(requireActivity(),iv_sxzy)
        }
        initData()
    }

    /**
     * 测试数据
     */
    private fun initData(){
        getServerData()
    }

    private val url = "https://open.douyin.com/discovery/ent/rank/item"
    private val url2="https://open.douyin.com/discovery/ent/rank/version/"
    private fun getServerData() {
        val TAG = "getServerData"
        OkGo.get<String>(url)
            .tag(this)
            .cacheKey("cachekey")
            .headers("access-token","clt.477cbe21940bb970f8d7951a33ede687krPVpYvf96gRjDtqAaUnjhbUdTEq")
            .params("type",3)
            .execute(
                object : StringCallback() {
                    @SuppressLint("WrongConstant") //Response<T> 写对应的后台返回的bean
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body().toString()
                        Log.d(TAG, "onSuccess: "+json)
                        val newsResponse = Gson().fromJson(json, bdResponse::class.java)
                        if (response.code() == 200) {
                            if (!TextUtils.isEmpty(response.body())) {
                                val da = newsResponse.data.list
                                val dat = ArrayList<ZyBean>()
                                val t1 = thread {
                                    for (i in da) {
                                        var dir:String = ""
                                        var are:String = ""
                                        if (i.directors == null){
                                            dir = ""
                                        } else {
                                            for (a in i.directors){
                                                dir += "$a "
                                            }
                                        }
                                        if (i.areas == null){
                                            are = ""
                                        } else {
                                            for (a in i.areas){
                                                are += "$a "
                                            }
                                        }
                                        val dsj = ZyBean(
                                            getURLimage(i.poster),
                                            i.name,
                                            i.name_en,
                                            dir,
                                            "讨论 "+i.discussion_hot,
                                            "主题 "+i.topic_hot,
                                            "搜索 "+i.search_hot,
                                            "影响力 "+i.influence_hot
                                        )
                                        dat.add(dsj)
                                    }
                                }
                                t1.join()
                                adapter?.clear()
                                adapter?.appendToList(dat)
                            }
                        }
                    }
                },
            )
    }

    private fun getURLimage(url:String): Bitmap {
        val TAG = "getURLimage"
        var bmp: Bitmap = BitmapFactory.decodeResource(resources,R.mipmap.head1)
        try {
            val myurl = URL(url)
            val conn: HttpURLConnection = myurl.openConnection() as HttpURLConnection
            conn.connect()
            val str: InputStream = conn.inputStream
            bmp = BitmapFactory.decodeStream(str)
            str.close()
        } catch (e:Exception){
            e.printStackTrace()
        }
        return bmp
    }
}