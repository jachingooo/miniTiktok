package com.test.miniTiktok.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.text.Spanned
import android.text.util.Linkify
import android.util.SparseArray
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

/**
 * @description 适配器基类-BaseHolder
 * @time:
 * @author:
 */
open class BaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    protected var mOnViewClickListener: OnViewClickListener? = null

    // SparseArray 比 HashMap 更省内存，在某些条件下性能更好，只能存储 key 为 int 类型的数据，
    // 用来存放 View 以减少 findViewById 的次数
    private var viewSparseArray: SparseArray<View>? = null

    //这个是item的对象
    private var mItemView: View? = null

    init {
        viewSparseArray = SparseArray<View>()
        mItemView = itemView
        mItemView?.setOnClickListener(this) //点击事件
    }

    /**
     * 根据 ID 来获取 View
     *
     * @param viewId viewID
     * @param <T>    泛型
     * @return 将结果强转为 View 或 View 的子类型
    </T> */
    fun <T : View?> getView(viewId: Int): T {
        // 先从缓存中找，找打的话则直接返回
        // 如果找不到则 findViewById ，再把结果存入缓存中
        var view = viewSparseArray!![viewId]
        if (view == null) {
            view = itemView.findViewById(viewId)
            viewSparseArray!!.put(viewId, view)
        }
        return view as T
    }

    /**
     * 在 Activity 的 onDestroy 中使用 [releaseAllHolder(RecyclerView)] 方法 (super.onDestroy() 之前)
     * [BaseHolder.onRelease] 才会被调用, 可以在此方法中释放一些资源
     */
    protected fun onRelease() {}

    override fun onClick(view: View?) {
        if (mOnViewClickListener != null) {
            mOnViewClickListener!!.onViewClick(view, this.layoutPosition)
        }
    }

    interface OnViewClickListener {
        fun onViewClick(view: View?, position: Int)
    }

    fun setOnItemClickListener(listener: OnViewClickListener?) {
        mOnViewClickListener = listener
    }

    /**
     * 获取item的对象
     */
    fun getItemView(): View? {
        return mItemView
    }


    /**
     * 设置TextView的值
     */
    fun setText(viewId: Int, text: String?): BaseHolder {
        val tv = getView<TextView>(viewId)
        tv.text = text
        return this
    }

    /**
     * 设置TextView的值
     */
    fun setText(viewId: Int, text: Spanned?): BaseHolder {
        val tv = getView<TextView>(viewId)
        tv.text = text
        return this
    }

    /**
     * 设置imageView图片
     */
    fun setImageResource(viewId: Int, resId: Int): BaseHolder {
        val view: ImageView = getView(viewId)
        view.setImageResource(resId)
        return this
    }

    /**
     * 设置imageView图片
     */
    fun setImageBitmap(viewId: Int, bitmap: Bitmap?): BaseHolder {
        val view: ImageView = getView(viewId)
        view.setImageBitmap(bitmap)
        return this
    }

    /**
     * 设置imageView图片
     */
    fun setImageDrawable(viewId: Int, drawable: Drawable?): BaseHolder {
        val view: ImageView = getView(viewId)
        view.setImageDrawable(drawable)
        return this
    }

    /**
     * 设置背景颜色
     */
    fun setBackgroundColor(viewId: Int, color: Int): BaseHolder {
        val view = getView<View>(viewId)
        view.setBackgroundColor(color)
        return this
    }

    /**
     * 设置背景颜色
     */
    fun setBackgroundRes(viewId: Int, backgroundRes: Int): BaseHolder {
        val view = getView<View>(viewId)
        view.setBackgroundResource(backgroundRes)
        return this
    }

    fun setTextSize(viewId: Int, textSize: Int): BaseHolder {
        val view = getView<TextView>(viewId)
        view.textSize = textSize.toFloat()
        return this
    }

    /**
     * 设置透明度
     */
    @SuppressLint("NewApi")
    fun setAlpha(viewId: Int, value: Float): BaseHolder {
        getView<View>(viewId).alpha = value
        return this
    }


    /**
     * 设置是否可见
     */
    fun setVisible(viewId: Int, visible: Boolean): BaseHolder {
        val view = getView<View>(viewId)!!
        view.visibility = if (visible) View.VISIBLE else View.GONE
        return this
    }

    /*public void setData(Object o, int position) {

    }*/

    /*public void setData(Object o, int position) {

    }*/
    fun setInvisible(viewId: Int): BaseHolder {
        val view = getView<View>(viewId)
        view.visibility = View.INVISIBLE
        return this
    }


    fun linkify(viewId: Int): BaseHolder {
        val view = getView<TextView>(viewId)
        Linkify.addLinks(view, Linkify.ALL)
        return this
    }

    fun setTypeface(typeface: Typeface?, vararg viewIds: Int): BaseHolder {
        for (viewId in viewIds) {
            val view = getView<TextView>(viewId)
            view.setTypeface(typeface)
            view.paintFlags = view.paintFlags or Paint.SUBPIXEL_TEXT_FLAG
        }
        return this
    }

    fun setTag(viewId: Int, tag: Any?): BaseHolder {
        val view = getView<View>(viewId)
        view.tag = tag
        return this
    }

    fun setTag(viewId: Int, key: Int, tag: Any?): BaseHolder {
        val view = getView<View>(viewId)
        view.setTag(key, tag)
        return this
    }

    fun setCheck(viewId: Int, check: Boolean): BaseHolder {
        val view = getView<CheckBox>(viewId)
        view.isChecked = check
        return this
    }

    fun setRadio(viewId: Int, check: Boolean): BaseHolder {
        val view = getView<RadioButton>(viewId)
        view.isChecked = check
        return this
    }
}