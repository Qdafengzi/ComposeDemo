package com.example.composedemo.ui.activity

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView

class WebViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                TopAppBar(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "back",
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .clickable {
                                finish()
                            },
                        textAlign = TextAlign.Left)
                    Text(text = "search", modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            Toast
                                .makeText(this@WebViewActivity, "search", Toast.LENGTH_SHORT)
                                .show()
                        }, textAlign = TextAlign.Right)
                }

                AndroidView(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), factory = { context ->
                    WebView(context).apply {
                        webViewClient= object :WebViewClient(){

                        }
                        webChromeClient = object :WebChromeClient(){

                        }
                        loadUrl("https://m.baidu.com/")
                    }
                })
            }

        }
    }
}