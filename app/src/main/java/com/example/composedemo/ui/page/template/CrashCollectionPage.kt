package com.example.composedemo.ui.page.template

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar

/**
 * Created by finn on 2022/5/6
 */
@Composable
fun CrashCollectionPage(navCtrl: NavHostController, title: String) {
    val scope = rememberCoroutineScope()
    CommonToolbar(navCtrl, title) {
       Button(onClick = {
           throw  RuntimeException()
       }) {
           Text(text = "点我崩溃")
       }
    }
}