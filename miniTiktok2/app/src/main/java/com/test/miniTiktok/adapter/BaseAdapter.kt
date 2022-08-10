package com.test.miniTiktok.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import com.alibaba.android.vlayout.DelegateAdapter
import com.alibaba.android.vlayout.LayoutHelper


/**
 * @description 适配器基类
 * @time:
 * @author:
 */
open class BaseAdapter<T> : DelegateAdapter.Adapter<BaseAdapter.BaseViewHolder> {

    protected var mLayoutHelper: LayoutHelper? = null
    protected var mCount = -1
    protected var mLayoutId = -1
    protected var mContext: Context? = null
    protected var mViewTypeItem = -1
    protected var mList: ArrayList<T> = ArrayList()
    protected var onItemClickListener: OnItemClickListener<T>? = null

    constructor(
            context: Context,
            layoutHelper: LayoutHelper?,
            layoutId: Int,
            list: ArrayList<T>,
            viewTypeItem: Int,
    ) : super() {
        mContext = context
        mLayoutHelper = layoutHelper
        mLayoutId = layoutId
        mViewTypeItem = viewTypeItem
        mList = list
    }

    constructor(
            context: Context,
            layoutHelper: LayoutHelper?,
            layoutId: Int,
            viewTypeItem: Int,
    ) : super() {
        mContext = context
        mLayoutHelper = layoutHelper
        mLayoutId = layoutId
        mViewTypeItem = viewTypeItem
    }


    /**
     * 获取列表数据
     */
    open fun getList(): List<T>? {
        return mList
    }

    open fun appendToTop(t: T?) {
        if (t == null) {
            return
        }
        mList.add(0, t)
        notifyItemInserted(0)
    }

    /**
     * 根据position 获取单个对象
     */
    open fun getItem(position: Int): T? {
        return if (position < 0 || position > mList!!.size - 1) {
            null
        } else mList!![position]
    }

    open fun append(t: T?) {
        if (t == null) {
            return
        }
        mList.add(t)
        notifyDataSetChanged()
    }

    open fun replaceItem(position: Int, item: T) {
        mList.set(position, item)
    }

    open fun removieBottom() {
        if (mList!!.size > 0) {
            mList.removeAt(mList!!.size - 1)
            notifyDataSetChanged()
        }
    }

    open fun removieByIndex(index: Int) {
        mList.removeAt(index)
        notifyItemRemoved(index)
        notifyItemRangeChanged(index,mList!!.size)
    }

    /**
     * 追加数据
     *
     * @param list
     */
    open fun appendToList(list: List<T>?) {
        if (list == null) {
            return
        }
        mList.addAll(list)
        notifyItemRangeInserted(itemCount, list.size)
    }

    /**
     * 追加数据
     *
     * @param list
     */
    open fun appendToTopList(list: List<T>?) {
        if (list == null) {
            return
        }
        mList.addAll(0, list)
        notifyDataSetChanged()
    }

    /**
     * 清空数据
     */
    open fun clear() {
        val i = itemCount
        mList.clear()
        notifyDataSetChanged()
    }


    open fun deleteAll() {
        mList.clear()
    }

    open fun replace(list: List<T>?) {
        if (list == null) {
            return
        }
        mList.clear()
        mList.addAll(0, list)
        notifyDataSetChanged()
    }


    override fun onCreateLayoutHelper(): LayoutHelper? {
        return mLayoutHelper
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(LayoutInflater.from(mContext).inflate(mLayoutId, parent, false))
    }

    override fun onBindViewHolder(@NonNull holder: BaseViewHolder, position: Int) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    onItemClickListener?.onItemClick(holder.itemView, mList!![position], position)
                }
            })
            holder.itemView.setOnLongClickListener(object : View.OnLongClickListener {
                override fun onLongClick(v: View): Boolean {
                    onItemClickListener?.onItemLongClick(
                            holder.itemView,
                            mList!![position],
                            position
                    )
                    return false
                }
            })
        }
    }

    /**
     * 必须重写不然会出现滑动不流畅的情况
     */
    override fun getItemViewType(position: Int): Int {
        return mViewTypeItem
    }

    /**
     * 条目数量
     * @return 条目数量
     */
    override fun getItemCount(): Int {
        return if (mList === null) mCount else mList!!.size
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener<T>?) {
        this.onItemClickListener = onItemClickListener
    }

    class BaseViewHolder(itemView: View) : BaseHolder(itemView)

    interface OnItemClickListener<T> {
        fun onItemClick(view: View, data: T, position: Int)
        fun onItemLongClick(view: View, data: T, position: Int)
    }
}