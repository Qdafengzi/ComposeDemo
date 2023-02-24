package com.example.composedemo.ui.page.tab

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.page.common.RouteName
import com.example.composedemo.ui.widget.ItemButton
import com.example.composedemo.utils.RouteUtils
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@InternalCoroutinesApi
@ExperimentalPagerApi
@Composable
fun HomePage(navCtrl: NavHostController) {

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            item {
                ItemButton(text = RouteName.HomeRoute.Row_Column) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.Row_Column)
                }
            }

            item {
                ItemButton(text = RouteName.HomeRoute.ModifierPage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.ModifierPage)
                }
            }

            item {
                ItemButton(text = RouteName.HomeRoute.TextPage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.TextPage)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.ConstrainLayoutPage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.ConstrainLayoutPage)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.BottomNavigation) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.BottomNavigation)
                }
            }

            item {
                ItemButton(text = RouteName.HomeRoute.ButtonPage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.ButtonPage)
                }
            }

            item {
                ItemButton(text = RouteName.HomeRoute.ClockPage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.ClockPage)
                }
            }

            item {
                ItemButton(text = RouteName.HomeRoute.ComposeWithXmlPage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.ComposeWithXmlPage)
                }
            }

            item {
                ItemButton(text = RouteName.HomeRoute.XmlWithComposePage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.XmlWithComposePage)
                }
            }

            item {
                ItemButton(text = RouteName.HomeRoute.GridListPage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.GridListPage)
                }
            }

            item {
                ItemButton(text = RouteName.HomeRoute.ImageCardPage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.ImageCardPage)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.ListPage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.ListPage)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.LazyColumnPage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.LazyColumnPage)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.ComplexPage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.ComplexPage)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.BezierPage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.BezierPage)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.CanvasLinePage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.CanvasLinePage)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.CanvasPage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.CanvasPage)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.ContentScreensPage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.ContentScreensPage)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.NavigationPage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.NavigationPage)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.NewUiStylePage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.NewUiStylePage)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.SnackBarPage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.SnackBarPage)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.StatePage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.StatePage)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.WebViewPage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.WebViewPage)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.MotionLayoutPage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.MotionLayoutPage)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.NestedScrollPage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.NestedScrollPage)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.LeftScrollDeletePage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.LeftScrollDeletePage)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.Goods3dImagePage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.Goods3dImagePage)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.Goods3dImagePageTwo) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.Goods3dImagePageTwo)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.Goods3dImagePageThree) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.Goods3dImagePageThree)
                }
            }

            item {
                ItemButton(text = RouteName.HomeRoute.SaveHandle) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.SaveHandle)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.RefreshPage) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.RefreshPage)
                }
            }

            item {
                ItemButton(text = RouteName.HomeRoute.Contact) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.Contact)
                }
            }
            item {
                ItemButton(text = RouteName.HomeRoute.Transformation) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.Transformation)
                }
            }

            item {
                ItemButton(text = RouteName.HomeRoute.RichText) {
                    RouteUtils.navTo(navCtrl, RouteName.HomeRoute.RichText)
                }
            }
        })
}


