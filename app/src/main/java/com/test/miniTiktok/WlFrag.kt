package com.test.miniTiktok

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
import com.test.miniTiktok.adapter.DyAdapter
import com.test.miniTiktok.bean.DyBean

class WlFrag :Fragment(){

    private var adapter:DyAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.frag_wl, null)
        initView(view)
        return view
    }
    private fun initView(v:View){
        var rv_main=v.findViewById<RecyclerView>(R.id.rv_main)
        var iv_sxwl=v.findViewById<ImageView>(R.id.iv_sxwl)
        adapter = DyAdapter(requireActivity())
        rv_main.adapter = adapter
        initData()

        iv_sxwl.setOnClickListener {
            PopWindowsUtil.showPop(requireActivity(),iv_sxwl)
        }
    }

    /**
     * 测试数据
     */
    private fun initData(){
        var testList= arrayListOf<DyBean>()
        testList.add(DyBean(R.mipmap.m1,"网络电影名称1","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m2,"网络电影名称2","作者2 / 张三2 / 李四2","2022-08-26","美国","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m3,"网络电影名称3","作者2 / 张三2 / 李四2","2022-08-26","日本","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m4,"网络电影名称4","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m5,"网络电影名称5","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m6,"网络电影名称6","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m7,"网络电影名称7","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m8,"网络电影名称8","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m9,"网络电影名称9","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DyBean(R.mipmap.m10,"网络电影名称10","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        adapter?.appendToList(testList)
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