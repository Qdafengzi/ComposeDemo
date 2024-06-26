package com.example.composedemo.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.webkit.WebView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.example.composedemo.state.AppViewModel
import com.example.composedemo.utils.XLogger
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

val appViewModel: AppViewModel by lazy { App.appViewModelInstance }


@HiltAndroidApp
class App : Application(), ViewModelStoreOwner {
    private lateinit var mAppViewModelStore: ViewModelStore

    private var mFactory: ViewModelProvider.Factory? = null

    override val viewModelStore: ViewModelStore
        get() = mAppViewModelStore


    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var CONTEXT: Context
        lateinit var mSWebView: WebView

        lateinit var appViewModelInstance: AppViewModel
    }

    override fun onCreate() {
        super.onCreate()
        var maxMemory = Runtime.getRuntime().maxMemory()/1024/1024
        XLogger.d("maxMemory====>$maxMemory")
        CONTEXT = this
        initWebView()
        MobileAds.initialize(this)
        mAppViewModelStore = ViewModelStore()
        appViewModelInstance = getAppViewModelProvider().get(AppViewModel::class.java)
        AppCrashHandler.instance.init(this)

    }


    /**
     * 获取一个全局的ViewModel
     */
    private fun getAppViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(this, this.getAppFactory())
    }

    private fun getAppFactory(): ViewModelProvider.Factory {
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        }
        return mFactory as ViewModelProvider.Factory
    }

    /**
     * 预热一个WebView
     */
    private fun initWebView() {
        mSWebView = WebView(CONTEXT)
    }

}