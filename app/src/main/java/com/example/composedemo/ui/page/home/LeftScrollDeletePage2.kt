package com.example.composedemo.ui.page.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.composedemo.ui.page.home.swipe.ScrollMotionSwipeDemo
import com.example.composedemo.ui.widget.CommonToolbar


@Composable
fun LeftScrollDeletePage2(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        ScrollMotionSwipeDemo()
    }
}