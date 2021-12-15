package com.example.composedemo.ui.page.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar


@Composable
fun MotionLayoutPage(navCtrl: NavHostController, title: String) {
    val context = LocalContext.current

    CommonToolbar(navCtrl, title) {

    }
}