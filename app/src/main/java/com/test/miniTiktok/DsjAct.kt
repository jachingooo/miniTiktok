package com.test.miniTiktok

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.test.miniTiktok.adapter.DsjAdapter
import com.test.miniTiktok.bean.DsjBean
import kotlinx.android.synthetic.main.act_dsj.*

class DsjAct : AppCompatActivity(){

    private var adapter: DsjAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_dsj)
        initView()
        initData()
    }
    private fun initView(){
        adapter = DsjAdapter(this)
        rv_main.adapter = adapter
        iv_sxdjs.setOnClickListener {
            PopWindowsUtil.showPop(this,iv_sxdjs)
        }
    }

    /**
     * 测试数据
     */
    private fun initData(){
        var testList= arrayListOf<DsjBean>()
        testList.add(DsjBean(R.mipmap.m1,"电视剧名称1","dianshijumingcheng1","爱情","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DsjBean(R.mipmap.m2,"电视剧名称2","dianshijumingcheng2","动作","作者2 / 张三2 / 李四2","2022-08-26","美国","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DsjBean(R.mipmap.m3,"电视剧名称3","dianshijumingcheng3","爱情","作者2 / 张三2 / 李四2","2022-08-26","日本","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DsjBean(R.mipmap.m4,"电视剧名称4","dianshijumingcheng4","爱情","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DsjBean(R.mipmap.m5,"电视剧名称5","dianshijumingcheng5","爱情","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DsjBean(R.mipmap.m6,"电视剧名称6","dianshijumingcheng6","爱情","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DsjBean(R.mipmap.m7,"电视剧名称7","dianshijumingcheng7","爱情","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DsjBean(R.mipmap.m8,"电视剧名称8","dianshijumingcheng8","爱情","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DsjBean(R.mipmap.m9,"电视剧名称9","dianshijumingcheng9","爱情","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
        testList.add(DsjBean(R.mipmap.m10,"电视剧名称10","dianshijumingcheng10","爱情","作者2 / 张三2 / 李四2","2022-08-26","大陆","讨论 6000.0万","主题 5000.0万","搜索 400.0万","影响力 3000.0万"))
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