package com.test.miniTiktok.adapter

import android.content.Context
import android.widget.ImageView
import com.alibaba.android.vlayout.layout.LinearLayoutHelper
import com.test.miniTiktok.R
import com.test.miniTiktok.bean.FsBean

/**
 * @description 关注、粉丝适配器
 * @time:
 * @author:
 */
class FsAdapter :BaseAdapter<FsBean> {

    private var mActivity: Context? = null

    constructor(activity: Context) : super(activity, LinearLayoutHelper(), R.layout.adapter_fs, 0) {
        mActivity = activity
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        var bean = mList.get(position)
        var iv_img=holder.getView<ImageView>(R.id.iv_img)
        var iv_xb=holder.getView<ImageView>(R.id.iv_xb)
        iv_img.setImageResource(bean?.url!!)
        holder.setText(R.id.tv_nc, bean?.name)
        holder.setText(R.id.tv_city, bean?.city)
        iv_xb.setImageResource(R.mipmap.touxiang)
    }
}