package com.github.yangkl.x.android.utils

import android.view.MotionEvent
import android.view.View
import android.widget.EditText

object XActivityUtils {
    /**
     * 判断用户点击的区域是否是输入框
     *
     * @param view
     * @param event
     */
    fun isTouchedInEditTextArea(view: View?, event: MotionEvent): Boolean {
        if (view is EditText) {
            val l = intArrayOf(0, 0)
            view.getLocationInWindow(l)
            val left = l[0]
            val top = l[1]
            val bottom = top + view.getHeight()
            val right = (left + view.getWidth())
            return !(event.x > left && event.x < right && event.y > top && event.y < bottom)
        }
        return false
    }
}