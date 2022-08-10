package com.test.miniTiktok

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.PopupWindow
import android.widget.TextView
import androidx.fragment.app.FragmentActivity

/**
 * @description PopWindows弹框工具栏
 * @time:
 * @author:
 */
object PopWindowsUtil{

    fun showPop(act:FragmentActivity,v: View) {
        val view: View = LayoutInflater.from(act).inflate(R.layout.pop_search, null)
        val popupWindow = PopupWindow(
            view,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        popupWindow.setBackgroundDrawable(act.resources.getDrawable(android.R.color.transparent))
        popupWindow.isOutsideTouchable = true
        val tv1 = view.findViewById<TextView>(R.id.tv1)
        val tv2 = view.findViewById<TextView>(R.id.tv2)
        val tv3 = view.findViewById<TextView>(R.id.tv3)
        val tv4 = view.findViewById<TextView>(R.id.tv4)
        tv1.setOnClickListener {
            popupWindow.dismiss()
        }
        tv2.setOnClickListener {
            popupWindow.dismiss()
        }
        tv3.setOnClickListener {
            popupWindow.dismiss()
        }
        tv4.setOnClickListener {
            popupWindow.dismiss()
        }
        popupWindow.showAsDropDown(v)
        //popupWindow在弹窗的时候背景半透明
        val params: WindowManager.LayoutParams = act.getWindow().getAttributes()
        params.alpha = 0.5f
        act.getWindow().setAttributes(params)
        popupWindow.setOnDismissListener {
            params.alpha = 1.0f
            act.getWindow().setAttributes(params)
        }
    }

}
