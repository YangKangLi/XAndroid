package com.github.yangkl.x.android.utils

import android.util.Log
import com.github.yangkl.x.android.base.XApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.File
import java.io.FileOutputStream
import java.util.*

object LogUtils {

    // 标记是否debug
    var debug: Boolean = false

    // 保证线程安全
    private val mutex = Mutex()

    fun d(tag: String, content: String) {
        if (debug) {
            Log.d(tag, content)
            write(content)
        }
    }

    /**
     * 写文件，使用Mutex保证线程安全
     */
    private fun write(content: String) {
        GlobalScope.launch(Dispatchers.IO) {
            mutex.withLock {
                doWrite(content)
            }
        }
    }

    /**
     * 最终写文件操作
     */
    private fun doWrite(content: String) {
        Log.d("Adam", "开始写 $content")
        val dir = File(XApp.instance.externalCacheDir, "logs")
        if (!dir.exists()) {
            dir.mkdirs()
        }
        val today = DTUtils.formatDate(Date(), "yyyyMMdd")
        val logFile = File(dir, "$today.log")
        val fos = FileOutputStream(logFile, true)
        val datetime = DTUtils.formatDate(Date(), "yyyy-MM-dd HH:mm:ss SSS")
        fos.write("$datetime $content\n".toByteArray())
        fos.flush()
        fos.close()
        Log.d("Adam", "结束写 $content")
    }
}