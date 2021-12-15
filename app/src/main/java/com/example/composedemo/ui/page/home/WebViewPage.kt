package com.example.composedemo.ui.page.home

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar


@Composable
fun WebViewPage(navCtrl: NavHostController, title: String) {
    val context = LocalContext.current

    CommonToolbar(navCtrl, title) {
        Column() {
            Column(modifier = Modifier.fillMaxSize()) {
                TopAppBar(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "back",
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .clickable {
                                navCtrl.popBackStack()
                            },
                        textAlign = TextAlign.Left
                    )
                    Text(text = "search", modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            Toast
                                .makeText(context, "search", Toast.LENGTH_SHORT)
                                .show()
                        }, textAlign = TextAlign.Right
                    )
                }

                AndroidView(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), factory = { context ->
                    WebView(context).apply {
                        webViewClient = object : WebViewClient() {

                        }
                        webChromeClient = object : WebChromeClient() {

                        }
                        loadUrl("https://m.baidu.com/")
                    }
                })
            }
        }
    }
}