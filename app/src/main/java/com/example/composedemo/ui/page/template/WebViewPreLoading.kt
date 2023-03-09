package com.example.composedemo.ui.page.template

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.composedemo.app.App.Companion.mSWebView
import com.example.composedemo.ui.widget.CommonToolbar

/**
 * WebView预热
 */
@Composable
fun WebViewPreLoading(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth(),
            factory = { ctx ->
//                mSWebView.loadUrl("file:///android_asset/test.html")
//                mSWebView.loadUrl("https://blog.csdn.net/XJ200012/article/details/128622414")
                mSWebView.loadUrl("https://juejin.cn/post/7204142890791026725")
                mSWebView
            },
        )
    }
}