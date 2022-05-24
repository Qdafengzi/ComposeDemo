package com.example.composedemo.ui.page.tab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.page.common.RouteName
import com.example.composedemo.ui.page.template.ChannelPage
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
    Column(
        modifier = Modifier
            .verticalScroll(state = rememberScrollState(), enabled = true)
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 80.dp),
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

        ItemButton(text = RouteName.TemplateRoute.ImageZoom) {
            RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.ImageZoom)
        }

        ItemButton(text = RouteName.TemplateRoute.SealedClass) {
            RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.SealedClass)
        }

        ItemButton(text = RouteName.TemplateRoute.DebouncedClickable) {
            RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.DebouncedClickable)
        }

        ItemButton(text = RouteName.TemplateRoute.ColorPicker) {
            RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.ColorPicker)
        }

        ItemButton(text = RouteName.TemplateRoute.Banner) {
            RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.Banner)
        }

        ItemButton(text = RouteName.TemplateRoute.StaggeredGridCompare) {
            RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.StaggeredGridCompare)
        }

        ItemButton(text =  RouteName.TemplateRoute.ChannelPage) {
            RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.ChannelPage)
        }

        ItemButton(text =  RouteName.TemplateRoute.GridView) {
            RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.GridView)
        }

        ItemButton(text =  RouteName.TemplateRoute.NetError) {
            RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.NetError)
        }

        ItemButton(text =  RouteName.TemplateRoute.SupportScreenSize) {
            RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.SupportScreenSize)
        }
    }
}