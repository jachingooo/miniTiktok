package com.test.miniTiktok.util

import android.R
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager


class CommonDecoration: RecyclerView.ItemDecoration {

    private val ATTRS = intArrayOf(R.attr.listDivider)
    private var mDivider: Drawable? = null

    constructor(context: Context) : super(){
        val a: TypedArray = context.obtainStyledAttributes(ATTRS)
        mDivider = a.getDrawable(0)
        a.recycle()
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        drawHorizontal(c, parent)
        drawVertical(c, parent)
    }

    private fun getSpanCount(parent: RecyclerView): Int {
        // 列数
        var spanCount = -1
        val layoutManager = parent.layoutManager
        if (layoutManager is GridLayoutManager) {
            spanCount = layoutManager.spanCount
        } else if (layoutManager is StaggeredGridLayoutManager) {
            spanCount = layoutManager
                .spanCount
        }
        return spanCount
    }

    fun drawHorizontal(c: Canvas, parent: RecyclerView) {
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child: View = parent.getChildAt(i)
            val params = child
                .getLayoutParams() as RecyclerView.LayoutParams
            val left: Int = child.getLeft() - params.leftMargin
            val right: Int = (child.getRight() + params.rightMargin
                    + mDivider!!.intrinsicWidth)
            val top: Int = child.getBottom() + params.bottomMargin
            val bottom = top + mDivider!!.intrinsicHeight
            mDivider!!.setBounds(left, top, right, bottom)
            mDivider!!.draw(c)
        }
    }

    fun drawVertical(c: Canvas, parent: RecyclerView) {
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child: View = parent.getChildAt(i)
            val params = child
                .getLayoutParams() as RecyclerView.LayoutParams
            val top: Int = child.getTop() - params.topMargin
            val bottom: Int = child.getBottom() + params.bottomMargin
            val left: Int = child.getRight() + params.rightMargin
            val right = left + mDivider!!.intrinsicWidth
            mDivider!!.setBounds(left, top, right, bottom)
            mDivider!!.draw(c)
        }
    }

    private fun isLastColum(
        parent: RecyclerView, pos: Int, spanCount: Int,
        childCount: Int
    ): Boolean {
        var childCount = childCount
        val layoutManager = parent.layoutManager
        if (layoutManager is GridLayoutManager) {
            // 如果是最后一列，则不需要绘制右边
            if ((pos + 1) % spanCount == 0) {
                return true
            }
        } else if (layoutManager is StaggeredGridLayoutManager) {
            val orientation = layoutManager
                .orientation
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                // 如果是最后一列，则不需要绘制右边
                if ((pos + 1) % spanCount == 0) {
                    return true
                }
            } else {
                childCount = childCount - childCount % spanCount
                if (pos >= childCount) { // 如果是最后一列，则不需要绘制右边
                    return true
                }
            }
        }
        return false
    }

    private fun isLastRaw(
        parent: RecyclerView,
        pos: Int,
        spanCount: Int,
        childCount: Int
    ): Boolean {
        var childCount = childCount
        val layoutManager = parent.layoutManager
        if (layoutManager is GridLayoutManager) {
            var last = childCount % spanCount
            if (last == 0) {
                last = spanCount
            }
            childCount = childCount - last
            if (pos >= childCount) { // 如果是最后一行，则不需要绘制底部
                return true
            }
        } else if (layoutManager is StaggeredGridLayoutManager) {
            val orientation = layoutManager
                .orientation
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                var last = childCount % spanCount
                if (last == 0) {
                    last = spanCount
                }
                childCount = childCount - last
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount) {
                    return true
                }
            } else { // StaggeredGridLayoutManager 且横向滚动
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0) {
                    return true
                }
            }
        }
        return false
    }

    override fun getItemOffsets(
        outRect: Rect, itemPosition: Int,
        parent: RecyclerView
    ) {
        val spanCount = getSpanCount(parent)
        val childCount = parent.adapter!!.itemCount
        if (isLastColum(parent, itemPosition, spanCount, childCount)) { // 如果是最后一列，则不需要绘制右边
            if (itemPosition == childCount - 1) {
                outRect.set(0, 0, 0, 0)
            } else {
                outRect.set(0, 0, 0, mDivider!!.intrinsicHeight)
            }
        } else if (isLastRaw(parent, itemPosition, spanCount, childCount)) { // 如果是最后一行，则不需要绘制底部
            outRect.set(0, 0, mDivider!!.intrinsicWidth, 0)
        } else {
            outRect.set(
                0, 0, mDivider!!.intrinsicWidth,
                mDivider!!.intrinsicHeight
            )
        }
    }
}