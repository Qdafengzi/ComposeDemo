package com.example.composedemo.ui.page.tab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.composedemo.ui.page.common.RouteName
import com.example.composedemo.ui.widget.ItemButton
import com.example.composedemo.utils.RouteUtils

@Composable
fun WidgetsPage(navCtrl: NavHostController) {

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        ItemButton(text = RouteName.WidgetsRoute.KeyboardPage) {
            RouteUtils.navTo(navCtrl, RouteName.WidgetsRoute.KeyboardPage)
        }

        ItemButton(text = RouteName.WidgetsRoute.ImagePaintPage) {
            RouteUtils.navTo(navCtrl, RouteName.WidgetsRoute.ImagePaintPage)
        }
    }

}