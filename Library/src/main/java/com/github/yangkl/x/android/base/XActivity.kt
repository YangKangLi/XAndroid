package com.github.yangkl.x.android.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

abstract class XActivity<VDB : ViewDataBinding, VM : XViewModel> : AppCompatActivity() {

    private lateinit var binding: VDB

    private var viewModel: VM? = null


    /**
     * 便于执行一些需要在setContentView之前的操作
     */
    open fun beforeOnSetContent() {
        // 子类实现
    }

    /**
     * 生命周期onCreate方法
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeOnSetContent()
        binding = DataBindingUtil.setContentView(this, getLayout())
        initView(binding)
        val factory = getViewModelFactory()
        viewModel = createViewModel(factory)
        viewModel?.let {
            lifecycle.addObserver(it)
            onViewModelCreated(it)
        }
    }

    /**
     * 获得布局资源ID
     */
    abstract fun getLayout(): Int

    /**
     * 初始化界面，注意：此时ViewModel还没创建
     */
    open fun initView(binding: VDB) {
        // 子类实现
    }

    /**
     * 获得ViewModel的factory
     */
    open fun getViewModelFactory(): ViewModelProvider.AndroidViewModelFactory {
        return ViewModelProvider.AndroidViewModelFactory(application)
    }

    /**
     * 获得ViewModel
     */
    open fun createViewModel(factory: ViewModelProvider.Factory): VM? {
        return null
    }

    /**
     * 当创建ViewModel成功时回调方法，viewModel不为null
     */
    open fun onViewModelCreated(viewModel: VM) {
        // 子类实现
    }
}