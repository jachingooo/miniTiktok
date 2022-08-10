package com.test.miniTiktok.adapter

import android.content.Context
import android.widget.ImageView
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.test.miniTiktok.R
import com.test.miniTiktok.bean.DsjBean


/**
 * @description 电视剧适配器
 * @time:
 * @author:
 */
class DsjAdapter :BaseAdapter<DsjBean> {

    private var mActivity: Context? = null

    constructor(activity: Context) : super(activity, LinearLayoutHelper(), R.layout.adapter_dsj, 0) {
        mActivity = activity
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if(position<=2)
            holder.setText(R.id.btn_pm,"TOP"+(position+1))
        else
            holder.setText(R.id.btn_pm,""+(position+1))
        var bean = mList.get(position)
        var iv_img=holder.getView<ImageView>(R.id.iv_img)
        iv_img.setImageResource(bean?.url!!)
        holder.setText(R.id.tv_name, bean.name+" "+bean.type)
        holder.setText(R.id.tv_ename, bean.nameEn)
        holder.setText(R.id.tv_dyandzz, bean.createUser)
        holder.setText(R.id.tv_time,bean.time+" 播出")
        holder.setText(R.id.tv_hots1,bean.hot1)
        holder.setText(R.id.tv_hots2,bean.hot2)
        holder.setText(R.id.tv_hots3,bean.hot3)
        holder.setText(R.id.tv_hots4,bean.hot4)
        holder.setText(R.id.tv_dq,bean.area)
    }
}