package com.example.composedemo.ui.page.tab

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.page.common.RouteName
import com.example.composedemo.ui.widget.ItemButton
import com.example.composedemo.utils.RouteUtils
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@InternalCoroutinesApi
@ExperimentalPagerApi
@Composable
fun HomePage(
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


        ItemButton(text = RouteName.HomeRoute.Row_Column) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.Row_Column)
        }

        ItemButton(text = RouteName.HomeRoute.ModifierPage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.ModifierPage)
        }

        ItemButton(text = RouteName.HomeRoute.TextPage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.TextPage)
        }

        ItemButton(text = RouteName.HomeRoute.ConstrainLayoutPage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.ConstrainLayoutPage)
        }

        ItemButton(text = RouteName.HomeRoute.BottomNavigation) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.BottomNavigation)
        }

        ItemButton(text = RouteName.HomeRoute.ButtonPage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.ButtonPage)
        }


        ItemButton(text = RouteName.HomeRoute.ClockPage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.ClockPage)
        }
        ItemButton(text = RouteName.HomeRoute.ComposeWithXmlPage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.ComposeWithXmlPage)
        }
        ItemButton(text = RouteName.HomeRoute.XmlWithComposePage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.XmlWithComposePage)
        }
        ItemButton(text = RouteName.HomeRoute.GridListPage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.GridListPage)
        }
        ItemButton(text = RouteName.HomeRoute.ImageCardPage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.ImageCardPage)
        }
        ItemButton(text = RouteName.HomeRoute.ListPage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.ListPage)
        }
        ItemButton(text = RouteName.HomeRoute.LazyColumnPage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.LazyColumnPage)
        }
        ItemButton(text = RouteName.HomeRoute.ComplexPage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.ComplexPage)
        }

        ItemButton(text = RouteName.HomeRoute.BezierPage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.BezierPage)
        }
        ItemButton(text = RouteName.HomeRoute.CanvasLinePage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.CanvasLinePage)
        }
        ItemButton(text = RouteName.HomeRoute.CanvasPage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.CanvasPage)
        }
        ItemButton(text = RouteName.HomeRoute.ContentScreensPage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.ContentScreensPage)
        }
        ItemButton(text = RouteName.HomeRoute.NavigationPage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.NavigationPage)
        }
        ItemButton(text = RouteName.HomeRoute.NewUiStylePage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.NewUiStylePage)
        }

        ItemButton(text = RouteName.HomeRoute.SnackBarPage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.SnackBarPage)
        }
        ItemButton(text = RouteName.HomeRoute.StatePage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.StatePage)
        }
        ItemButton(text = RouteName.HomeRoute.WebViewPage) {
            RouteUtils.navTo(navCtrl, RouteName.HomeRoute.WebViewPage)
        }
    }
}


