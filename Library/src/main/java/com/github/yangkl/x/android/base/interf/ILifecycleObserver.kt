package com.github.yangkl.x.android.base.interf

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

interface ILifecycleObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    open abstract fun onAny(owner: LifecycleOwner?, event: Lifecycle.Event?)

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    open abstract fun onCreate()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open abstract fun onDestroy()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open abstract fun onStart()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open abstract fun onStop()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open abstract fun onResume()

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open abstract fun onPause()
}