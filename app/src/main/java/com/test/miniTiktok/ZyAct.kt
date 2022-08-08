package com.test.miniTiktok

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.test.miniTiktok.adapter.ZyAdapter
import com.test.miniTiktok.bean.ZyBean
import kotlinx.android.synthetic.main.act_zy.*

class ZyAct : AppCompatActivity(){
    private var adapter: ZyAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_zy)
        initView()
        initData()
    }
    private fun initView(){
        adapter = ZyAdapter(this)
        rv_main.adapter = adapter
        iv_sxzy.setOnClickListener {
            PopWindowsUtil.showPop(this,iv_sxzy)
        }
    }

    /**
     * 测试数据
     */
    private fun initData(){
        var testList= arrayListOf<ZyBean>()
        testList.add(ZyBean(R.mipmap.m1,"综艺名称1","zongyimingcheng1","作者2","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(ZyBean(R.mipmap.m2,"综艺名称2","zongyimingcheng2","作者2","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(ZyBean(R.mipmap.m3,"综艺名称3","zongyimingcheng3","作者2","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(ZyBean(R.mipmap.m4,"综艺名称4","zongyimingcheng4","作者2","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(ZyBean(R.mipmap.m5,"综艺名称5","zongyimingcheng5","作者2","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(ZyBean(R.mipmap.m6,"综艺名称6","zongyimingcheng6","作者2","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(ZyBean(R.mipmap.m7,"综艺名称7","zongyimingcheng7","作者2","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(ZyBean(R.mipmap.m8,"综艺名称8","zongyimingcheng8","作者2","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(ZyBean(R.mipmap.m9,"综艺名称9","zongyimingcheng9","作者2","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(ZyBean(R.mipmap.m10,"综艺名称10","zongyimingcheng10","作者2","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
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