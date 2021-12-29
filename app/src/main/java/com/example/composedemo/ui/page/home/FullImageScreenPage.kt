package com.example.composedemo.ui.page.home

import android.view.LayoutInflater
import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.composedemo.R
import com.example.composedemo.ui.widget.CommonToolbar
import com.zph.glpanorama.GLPanorama

@Composable
fun FullImageScreenPage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            factory = { ctx ->
                val view = LayoutInflater.from(ctx).inflate(R.layout.activity_full_screen, null)
                view.findViewById<GLPanorama>(R.id.mGLPanorama).setGLPanorama(R.mipmap.imgbug)
                view
            },
        )
    }
}