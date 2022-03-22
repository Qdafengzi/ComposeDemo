package com.example.composedemo.ui.page.tab

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.page.common.RouteName
import com.example.composedemo.ui.widget.ItemButton
import com.example.composedemo.utils.RouteUtils
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun AnimPage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        mainAxisSpacing = 10.dp
    ) {
        ItemButton(text = RouteName.AimRoute.MarqueePage) {
            RouteUtils.navTo(navCtrl, RouteName.AimRoute.MarqueePage)
        }

        ItemButton(text = RouteName.AimRoute.Aim1) {
            RouteUtils.navTo(navCtrl, RouteName.AimRoute.Aim1)
        }

        ItemButton(text = RouteName.AimRoute.MoreText) {
            RouteUtils.navTo(navCtrl, RouteName.AimRoute.MoreText)
        }
    }

}