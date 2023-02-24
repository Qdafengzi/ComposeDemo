package com.example.composedemo.ui.page.tab

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun AnimPage(navCtrl: NavHostController) {
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

        ItemButton(text = RouteName.AimRoute.TouchAnimation) {
            RouteUtils.navTo(navCtrl, RouteName.AimRoute.TouchAnimation)
        }

        ItemButton(text = RouteName.AimRoute.TransformPage) {
            RouteUtils.navTo(navCtrl, RouteName.AimRoute.TransformPage)
        }

        ItemButton(text = RouteName.AimRoute.VoteAnimation) {
            RouteUtils.navTo(navCtrl, RouteName.AimRoute.VoteAnimation)
        }

        ItemButton(text = RouteName.AimRoute.MotionLayoutAnimation) {
            RouteUtils.navTo(navCtrl, RouteName.AimRoute.MotionLayoutAnimation)
        }

        ItemButton(text = RouteName.AimRoute.MotionLayoutAnimationTwo) {
            RouteUtils.navTo(navCtrl, RouteName.AimRoute.MotionLayoutAnimationTwo)
        }

        ItemButton(text = RouteName.AimRoute.MotionLayoutAnimation3) {
            RouteUtils.navTo(navCtrl, RouteName.AimRoute.MotionLayoutAnimation3)
        }

        ItemButton(text = RouteName.AimRoute.Recomposition) {
            RouteUtils.navTo(navCtrl, RouteName.AimRoute.Recomposition)
        }

        ItemButton(text = RouteName.AimRoute.CanvasAnimation) {
            RouteUtils.navTo(navCtrl, RouteName.AimRoute.CanvasAnimation)
        }
    }

}