package com.example.composedemo.ui.page.tab

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.page.common.RouteName
import com.example.composedemo.ui.widget.ItemButton
import com.example.composedemo.utils.RouteUtils

@Composable
fun AnimPage(navCtrl: NavHostController) {

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {

            item {
                ItemButton(text = RouteName.AimRoute.MarqueePage) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.MarqueePage)
                }
            }

            item {

                ItemButton(text = RouteName.AimRoute.Aim1) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.Aim1)
                }
            }
            item {
                ItemButton(text = RouteName.AimRoute.MoreText) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.MoreText)
                }
            }
            item {
                ItemButton(text = RouteName.AimRoute.TouchAnimation) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.TouchAnimation)
                }
            }
            item {
                ItemButton(text = RouteName.AimRoute.TransformPage) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.TransformPage)
                }
            }
            item {

                ItemButton(text = RouteName.AimRoute.VoteAnimation) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.VoteAnimation)
                }
            }
            item {
                ItemButton(text = RouteName.AimRoute.MotionLayoutAnimation) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.MotionLayoutAnimation)
                }
            }
            item {

                ItemButton(text = RouteName.AimRoute.MotionLayoutAnimationTwo) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.MotionLayoutAnimationTwo)
                }
            }
            item {

                ItemButton(text = RouteName.AimRoute.MotionLayoutAnimation3) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.MotionLayoutAnimation3)
                }
            }
            item {

                ItemButton(text = RouteName.AimRoute.Recomposition) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.Recomposition)
                }
            }
            item {

                ItemButton(text = RouteName.AimRoute.CanvasAnimation) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.CanvasAnimation)
                }
            }
            item {

                ItemButton(text = RouteName.AimRoute.SkyAnimation) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.SkyAnimation)
                }
            }
            item {

                ItemButton(text = RouteName.AimRoute.BannerAnimation) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.BannerAnimation)
                }
            }
            item {

                ItemButton(text = RouteName.AimRoute.TextMarqueeAnimation) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.TextMarqueeAnimation)
                }
            }

            item {
                ItemButton(text = RouteName.AimRoute.PrintText) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.PrintText)
                }
            }

            item {
                ItemButton(text = RouteName.AimRoute.LeftScrollSelect) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.LeftScrollSelect)
                }
            }

            item {
                ItemButton(text = RouteName.AimRoute.Refresh1) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.Refresh1)
                }
            }

            item {
                ItemButton(text = RouteName.AimRoute.Refresh2) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.Refresh2)
                }
            }

            item {
                ItemButton(text = RouteName.AimRoute.Refresh3) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.Refresh3)
                }
            }

            item {
                ItemButton(text = RouteName.AimRoute.Refresh4) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.Refresh4)
                }
            }

            item {
                ItemButton(text = RouteName.AimRoute.Refresh5) {
                    RouteUtils.navTo(navCtrl, RouteName.AimRoute.Refresh5)
                }
            }
        })

}