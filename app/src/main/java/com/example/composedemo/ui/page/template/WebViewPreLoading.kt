package com.example.composedemo.ui.page.template

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
                .fillMaxWidth()
                .padding(8.dp),
            factory = { ctx ->
                mSWebView.loadUrl("file:///android_asset/test.html")
                mSWebView
            },
        )
    }
}