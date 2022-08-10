package com.test.miniTiktok.frag

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.test.miniTiktok.PopWindowsUtil
import com.test.miniTiktok.R
import com.test.miniTiktok.adapter.DsjAdapter
import com.test.miniTiktok.util.TestDataBean

/**
 * @description 电视剧页面Fragment
 * @time:
 * @author:
 */
class DsjFragment : Fragment(){

    private var adapter: DsjAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.frag_dsj, null)
        initView(view)
        return view
    }
    private fun initView(v: View){
        var rv_main=v.findViewById<RecyclerView>(R.id.rv_main)
        var iv_sxdjs=v.findViewById<ImageView>(R.id.iv_sxdjs)
        adapter = DsjAdapter(requireActivity())
        rv_main.adapter = adapter
        iv_sxdjs.setOnClickListener {
            PopWindowsUtil.showPop(requireActivity(),iv_sxdjs)
        }
        initData()
    }

    /**
     * 测试数据
     */
    private fun initData(){
        adapter?.appendToList(TestDataBean.getDsjData())
    }

    private val url = "写自己的接口地址"
    private fun getServerData() {
        OkGo.post<String>(url)
            .tag(this)
            .params("入参key","")
            .execute(object : StringCallback() {
                @SuppressLint("WrongConstant") //Response<T> 写对应的后台返回的bean
                override fun onSuccess(response: Response<String>) {
                    if (response.code() == 200) {
                        if (!TextUtils.isEmpty(response.body())) {
                            //解析服务器返回的数据
                        }
                    }
                }
            })
    }
}