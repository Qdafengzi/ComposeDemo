package com.example.composedemo.ui.page.tab

import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.composedemo.ui.theme.Co_green
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun AnimPage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
) {
    Text(text = "Anim",color = Co_green)
}