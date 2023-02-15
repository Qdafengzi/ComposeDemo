package com.example.composedemo.ui.page.home

import android.util.Log
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import jp.wasabeef.richeditor.RichEditor
import jp.wasabeef.richeditor.RichEditor.OnTextChangeListener


/**
 * webview实现的 不好玩
 */
@Composable
fun RichTextPage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        AndroidView(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(color = Color.LightGray),
            factory = { context ->
                val richEditor = RichEditor(context)
                richEditor.setBackgroundColor(android.graphics.Color.MAGENTA)
                val layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                )
                richEditor.layoutParams = layoutParams
                richEditor.setOnTextChangeListener(OnTextChangeListener { text -> // Do Something

                    //richEditor.setHeading((2..5).random())

                    Log.d("RichEditor", "Preview $text")
                })
                richEditor
            })
    }
}

