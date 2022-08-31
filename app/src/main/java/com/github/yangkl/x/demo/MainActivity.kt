package com.github.yangkl.x.demo

import android.os.Bundle
import com.github.yangkl.x.android.base.XActivity
import com.github.yangkl.x.android.utils.LogUtils
import com.github.yangkl.x.demo.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : XActivity<ActivityMainBinding, MainViewModel>() {


    override fun getLayout(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.debug = true

        Thread {
            for (i in 0..100) {
                LogUtils.d("Main", "MainActivity onCreated $i")
            }
        }.start()
    }
}