package com.github.yangkl.x.android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract class XFragment<VDB : ViewDataBinding, VM : XViewModel> : Fragment() {

    private lateinit var binding: VDB

    private var viewModel: VM? = null


    /**
     * 创建View
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        initView(binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        return ViewModelProvider.AndroidViewModelFactory(XApp.instance)
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

    /**
     * 得到ViewBinding
     */
    fun getBinding(): VDB = binding

    /**
     * 获得ViewModel
     */
    fun getViewModel(): VM? = viewModel
}