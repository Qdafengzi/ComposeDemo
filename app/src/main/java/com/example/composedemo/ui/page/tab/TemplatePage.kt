package com.example.composedemo.ui.page.tab

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.page.common.RouteName
import com.example.composedemo.ui.widget.ItemButton
import com.example.composedemo.utils.RouteUtils
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun TemplatePage(navCtrl: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 80.dp),
    ) {
        // column contents
        item {
            ItemButton(text = RouteName.TemplateRoute.CameraPage) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.CameraPage)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.PermissionPage) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.PermissionPage)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.JingDongPage) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.JingDongPage)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.MLKitPage) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.MLKitPage)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.SmartRefreshPage) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.SmartRefreshPage)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.WechatFriendsCirclePage) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.WechatFriendsCirclePage)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.ImageZoom) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.ImageZoom)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.SealedClass) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.SealedClass)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.DebouncedClickable) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.DebouncedClickable)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.ColorPicker) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.ColorPicker)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.Banner) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.Banner)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.StaggeredGridCompare) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.StaggeredGridCompare)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.ChannelPage) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.ChannelPage)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.GridView) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.GridView)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.NetError) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.NetError)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.SupportScreenSize) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.SupportScreenSize)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.CanvasPageTwo) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.CanvasPageTwo)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.WebView) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.WebView)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.StaggeredGrid_office) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.StaggeredGrid_office)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.Youtube_page) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.Youtube_page)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.Youtube_Rv_page) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.Youtube_Rv_page)
            }
        }

        item {
            ItemButton(text = RouteName.TemplateRoute.BlurPage) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.BlurPage)
            }
        }
        item {
            ItemButton(text = RouteName.TemplateRoute.WebViewPreLoading) {
                RouteUtils.navTo(navCtrl, RouteName.TemplateRoute.WebViewPreLoading)
            }
        }

    }
}