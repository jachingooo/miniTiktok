package com.qxy.miniTiktok.frag

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.qxy.miniTiktok.R
import com.qxy.miniTiktok.adapter.FsAdapter
import com.qxy.miniTiktok.bean.FsBean
import com.qxy.miniTiktok.bean.FsResBean
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

/**
 * @description 粉丝页面Fragment
 * @time:
 * @author:
 */
class FsFragment : Fragment(){
    private var adapter: FsAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.frag_fs, null)
        initView(view)
        return view
    }
    private fun initView(v: View){
        var rv_main=v.findViewById<RecyclerView>(R.id.rv_main)
        adapter = FsAdapter(requireActivity())
        rv_main.adapter = adapter
        initData()
    }

    /**
     * 测试数据
     */
    private fun initData(){
        //adapter?.appendToList(TestDataBean.getFsData())
        getServerData()
    }


    private val url = "https://open.douyin.com/fans/list/"


    private fun getServerData() {

        OkGo.get<String>(url)
            .tag(this)
            .params("count", "10")
            .params("open_id", "_000ZjFPfB26u0nnppodCXU4uKrWTtYogwlf")
            .headers("access-token","act.689358472bd7c60133ef7fb1e925c9e3QKkZbuOeuLUKuo4q6dBstsshdw8y")
            .execute(object : StringCallback() {
                @SuppressLint("WrongConstant") //Response<T> 写对应的后台返回的bean
                override fun onSuccess(response: Response<String>) {
                    val jsonRs = response.body().toString()

                    val res = Gson().fromJson(jsonRs, FsResBean::class.java)
                    if (response.code() == 200) {
                        if (!TextUtils.isEmpty(response.body())) {
                            val Fsdata = res.data.list
                            val FsList = ArrayList<FsBean>()
                            val t1 = thread {
                                for (i in Fsdata) {

                                    val Fs = FsBean(
                                        getURLimage(i.avatar),
                                        i.nickname,
                                        when (i.gender) {
                                            1 -> "男"
                                            2 -> "女"
                                            else -> {
                                                ""
                                            }
                                        },
                                        i.country + " " + i.province + " " + i.city
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