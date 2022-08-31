package com.github.yangkl.x.android.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object DTUtils {

    /**
     * 格式化时间
     *
     * @param date
     * @param pattern
     * @return
     */
    fun formatDate(date: Date?, pattern: String?): String? {
        try {
            val sdf = SimpleDateFormat(pattern, Locale.getDefault())
            return sdf.format(date!!)
        } catch (ex: Exception) {
            //Log.d(DTUtils.TAG, "调用DTUtils.formatDate()发生异常：" + ex.message)
        }
        return null
    }
}