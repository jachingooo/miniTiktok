package com.test.miniTiktok.adapter

import android.content.Context
import android.widget.RelativeLayout
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.test.miniTiktok.R
import com.test.miniTiktok.bean.ZpBean

/**
 * @description 抖音作品适配器
 * @time:
 * @author:
 */
class ZpAdapter  :BaseAdapter<ZpBean> {

    private var mActivity: Context? = null

    constructor(activity: Context) : super(activity, LinearLayoutHelper(), R.layout.adapter_zp, 0) {
        mActivity = activity
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        var bean = mList.get(position)
        var layout_fm=holder.getView<RelativeLayout>(R.id.layout_fm)
        //layout_fm.setBackgroundResource(bean?.url!!)
        holder.setVisible(R.id.btn_zd,bean?.isTop!!)
        holder.setText(R.id.tv_title, bean.title)
        holder.setText(R.id.tv_time, bean.title)
        holder.setText(R.id.tv_bfnum,""+bean.playcount)
        holder.setText(R.id.tv_plnum, ""+bean.plcount)

    }
}