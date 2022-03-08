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
fun TemplatePage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        mainAxisSpacing = 10.dp
    ) {
        // column contents
        ItemButton(text = RouteName.TemplateRoute.CameraPage) {
            RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.CameraPage)
        }

        ItemButton(text = RouteName.TemplateRoute.PermissionPage) {
            RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.PermissionPage)
        }

        ItemButton(text = RouteName.TemplateRoute.JingDongPage) {
            RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.JingDongPage)
        }

        ItemButton(text = RouteName.TemplateRoute.MLKitPage) {
            RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.MLKitPage)
        }

        ItemButton(text = RouteName.TemplateRoute.SmartRefreshPage) {
            RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.SmartRefreshPage)
        }

        ItemButton(text = RouteName.TemplateRoute.WechatFriendsCirclePage) {
            RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.WechatFriendsCirclePage)
        }

        ItemButton(text = RouteName.TemplateRoute.ImageScale) {
            RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.ImageScale)
        }
    }
}