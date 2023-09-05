package com.example.composedemo.ui.page.template

import android.graphics.Bitmap
import android.net.http.SslError
import android.webkit.ClientCertRequest
import android.webkit.ConsoleMessage
import android.webkit.HttpAuthHandler
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.composedemo.BuildConfig
import com.example.composedemo.ui.widget.CommonToolbar
import com.example.composedemo.utils.XLogger
import com.google.accompanist.web.AccompanistWebChromeClient
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun WebViewPageNew(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
//        val state = rememberWebViewState("https://www.baidu.com")
        val state = rememberWebViewState("https://www.jianshu.com/p/b937bee08bf8")
        WebView.setWebContentsDebuggingEnabled(true)
        WebView(state = state, modifier = Modifier.fillMaxSize(),
            onCreated = {
                XLogger.d("onCreated----------->")
                it.settings.apply {
                    javaScriptEnabled = true
                    //设置自适应屏幕，两者合用
                    useWideViewPort = true
                    loadWithOverviewMode = true
                    //支持缩放，默认为true。是下面那个的前提。
                    setSupportZoom(true)
                    //设置内置的缩放控件。若为false，则该WebView不可缩放
                    builtInZoomControls = true
                    //隐藏原生的缩放控件
                    displayZoomControls = false
                    //设置可以访问文件
                    allowFileAccess = true
                    domStorageEnabled = true

                    //支持通过JS打开新窗口
                    javaScriptCanOpenWindowsAutomatically = true
                    //支持自动加载图片
                    loadsImagesAutomatically = true
                    defaultTextEncodingName = "utf-8"
                }
            },
            onDispose = {
                XLogger.d("onDispose----------->")
            },
            client = object : AccompanistWebViewClient() {
                override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) {
                    super.onReceivedHttpError(view, request, errorResponse)
                    XLogger.d("------>${errorResponse?.data.toString()}")
                }

                override fun onReceivedLoginRequest(view: WebView?, realm: String?, account: String?, args: String?) {
                    XLogger.d("onReceivedLoginRequest--------->$realm")
                    super.onReceivedLoginRequest(view, realm, account, args)

                }

                override fun onLoadResource(view: WebView?, url: String?) {
                    XLogger.d("onLoadResource-------->$url")
                    if(url=="jianshu://notes/b937bee08bf8"){
                        XLogger.d("----->hhhh")
                    }else{
                        super.onLoadResource(view, url)
                    }
                }

                override fun onReceivedClientCertRequest(view: WebView?, request: ClientCertRequest?) {
                    super.onReceivedClientCertRequest(view, request)
                    XLogger.d("onReceivedClientCertRequest-------->${request?.host}")
                }

                override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {

                    if(url=="jianshu://notes/b937bee08bf8"){

                    }else {
                        view?.loadUrl(url?:"")
                        super.onPageStarted(view, url, favicon)
                    }
//                    super.onPageStarted(view, url, favicon)
                    XLogger.d("onPageStarted------------->$url")
                }

                override fun onReceivedHttpAuthRequest(view: WebView?, handler: HttpAuthHandler?, host: String?, realm: String?) {
                    XLogger.d("onReceivedHttpAuthRequest--------->$host")
                    super.onReceivedHttpAuthRequest(view, handler, host, realm)
                }

                override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                    super.onReceivedSslError(view, handler, error)
                    XLogger.d("onReceivedSslError--------->${error.toString()}")
                }
            },
            chromeClient = object : AccompanistWebChromeClient() {

                override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
                    return if (BuildConfig.DEBUG) {
                        XLogger.d(
                            "webView log------" + consoleMessage?.message() + " -- From line " + consoleMessage?.lineNumber() + " of " + consoleMessage?.sourceId()
                        )
                        true
                    } else {
                        super.onConsoleMessage(consoleMessage)
                    }
                }
            }
        )
    }
}

