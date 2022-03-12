package com.github.yangkl.x.android.base

import android.app.Application

class XApp: Application() {

    companion object{
        lateinit var instance: XApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}