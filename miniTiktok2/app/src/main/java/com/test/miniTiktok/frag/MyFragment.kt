package com.test.miniTiktok.frag

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.test.miniTiktok.R
import com.test.miniTiktok.act.FansAct
import com.test.miniTiktok.act.MovieActivity
import com.test.miniTiktok.adapter.BaseAdapter
import com.test.miniTiktok.adapter.ZpAdapter
import com.test.miniTiktok.bean.ZpBean
import com.test.miniTiktok.util.CommonDecoration
import com.test.miniTiktok.util.TestDataBean

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
        adapter?.appendToList(TestDataBean.getMovieData())
    }

    private val url = "写自己的接口地址"
    private fun getServerData() {
        OkGo.post<String>(url)
            .tag(this)
            .params("入参key", "")
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