package com.github.yangkl.x.demo

import com.github.yangkl.x.android.base.XActivity
import com.github.yangkl.x.demo.databinding.ActivityMainBinding

class MainActivity : XActivity<ActivityMainBinding, MainViewModel>() {


    override fun getLayout(): Int = R.layout.activity_main
}