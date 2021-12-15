package com.example.composedemo.ui.page.tab

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.composedemo.ui.page.common.RouteName
import com.example.composedemo.ui.widget.ItemButton
import com.example.composedemo.utils.RouteUtils
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun WidgetsPage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
) {
    ItemButton(text = RouteName.WidgetsRoute.KeyboardPage) {
        RouteUtils.navTo(navCtrl, RouteName.WidgetsRoute.KeyboardPage)
    }
}