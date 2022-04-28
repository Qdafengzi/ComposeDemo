package com.example.composedemo.ui.page

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composedemo.ui.page.anim.Aim1Page
import com.example.composedemo.ui.page.anim.MarqueePage
import com.example.composedemo.ui.page.anim.MoreText
import com.example.composedemo.ui.page.common.BottomNavBarView
import com.example.composedemo.ui.page.common.RouteName
import com.example.composedemo.ui.page.home.*
import com.example.composedemo.ui.page.tab.AnimPage
import com.example.composedemo.ui.page.tab.HomePage
import com.example.composedemo.ui.page.tab.TemplatePage
import com.example.composedemo.ui.page.tab.WidgetsPage
import com.example.composedemo.ui.page.template.*
import com.example.composedemo.ui.page.widgets.KeyboardPage
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.InternalCoroutinesApi

@RequiresApi(Build.VERSION_CODES.R)
@InternalCoroutinesApi
@ExperimentalPermissionsApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun AppScaffold() {
    val navCtrl = rememberNavController()
    val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        bottomBar = {
            when (currentDestination?.route) {
                RouteName.HOME -> BottomNavBarView(navCtrl = navCtrl)
                RouteName.WIDGETS -> BottomNavBarView(navCtrl = navCtrl)
                RouteName.Anim -> BottomNavBarView(navCtrl = navCtrl)
                RouteName.Template -> BottomNavBarView(navCtrl = navCtrl)
            }
        },
        content = {
            NavHost(
                modifier = Modifier.background(MaterialTheme.colors.background),
                navController = navCtrl,
                startDestination = RouteName.HOME
            ) {
                //首页
                composable(route = RouteName.HOME) {
                    HomePage(navCtrl, scaffoldState)
                }

                composable(route = RouteName.WIDGETS) {
                    WidgetsPage(navCtrl, scaffoldState)
                }
                composable(route = RouteName.Anim) {
                    AnimPage(navCtrl, scaffoldState)
                }
                composable(route = RouteName.Template) {
                    TemplatePage(navCtrl, scaffoldState)
                }


                //Home 页面 内容
                composable(route = RouteName.HomeRoute.Row_Column) {
                    RowAndColumn(navCtrl, RouteName.HomeRoute.Row_Column)
                }

                composable(route = RouteName.HomeRoute.ModifierPage) {
                    ModifierPage(navCtrl, RouteName.HomeRoute.ModifierPage)
                }

                composable(route = RouteName.HomeRoute.TextPage) {
                    TextPage(navCtrl, RouteName.HomeRoute.TextPage)
                }

                composable(route = RouteName.HomeRoute.ConstrainLayoutPage) {
                    ConstraintLayoutPage(navCtrl, RouteName.HomeRoute.ConstrainLayoutPage)
                }

                composable(route = RouteName.HomeRoute.BottomNavigation) {
                    BottomNavigationPage(navCtrl, RouteName.HomeRoute.BottomNavigation)
                }

                composable(route = RouteName.HomeRoute.ButtonPage) {
                    ButtonPage(navCtrl, RouteName.HomeRoute.ButtonPage)
                }


                composable(route = RouteName.HomeRoute.ClockPage) {
                    ClockPage(navCtrl, RouteName.HomeRoute.ClockPage)
                }

                composable(route = RouteName.HomeRoute.ComposeWithXmlPage) {
                    ComposeWithXmlPage(navCtrl, RouteName.HomeRoute.ComposeWithXmlPage)
                }

                composable(route = RouteName.HomeRoute.XmlWithComposePage) {
                    XmlWithComposePage(navCtrl, RouteName.HomeRoute.XmlWithComposePage)
                }

                composable(route = RouteName.HomeRoute.GridListPage) {
                    GridListPage(navCtrl, RouteName.HomeRoute.GridListPage)
                }

                composable(route = RouteName.HomeRoute.ImageCardPage) {
                    ImageCardPage(navCtrl, RouteName.HomeRoute.ImageCardPage)
                }

                composable(route = RouteName.HomeRoute.ListPage) {
                    ListPage(navCtrl, RouteName.HomeRoute.ListPage)
                }

                composable(route = RouteName.HomeRoute.LazyColumnPage) {
                    LazyColumnPage(navCtrl, RouteName.HomeRoute.LazyColumnPage)
                }

                composable(route = RouteName.HomeRoute.ComplexPage) {
                    ComplexPage(navCtrl, RouteName.HomeRoute.ComplexPage)
                }

                composable(route = RouteName.HomeRoute.BezierPage) {
                    BezierPage(navCtrl, RouteName.HomeRoute.BezierPage)
                }

                composable(route = RouteName.HomeRoute.CanvasLinePage) {
                    CanvasLinePage(navCtrl, RouteName.HomeRoute.CanvasLinePage)
                }

                composable(route = RouteName.HomeRoute.CanvasPage) {
                    CanvasPage(navCtrl, RouteName.HomeRoute.CanvasPage)
                }

                composable(route = RouteName.HomeRoute.ContentScreensPage) {
                    ContentScreensPage(navCtrl, RouteName.HomeRoute.ContentScreensPage)
                }
                composable(route = RouteName.HomeRoute.NavigationPage) {
                    NavigationPage(navCtrl, RouteName.HomeRoute.NavigationPage)
                }
                composable(route = RouteName.HomeRoute.NewUiStylePage) {
                    NewUiStylePage(navCtrl, RouteName.HomeRoute.NewUiStylePage)
                }
                composable(route = RouteName.HomeRoute.SnackBarPage) {
                    SnackBarPage(navCtrl, RouteName.HomeRoute.SnackBarPage)
                }
                composable(route = RouteName.HomeRoute.StatePage) {
                    StatePage(navCtrl, RouteName.HomeRoute.StatePage)
                }
                composable(route = RouteName.HomeRoute.WebViewPage) {
                    WebViewPage(navCtrl, RouteName.HomeRoute.WebViewPage)
                }

                composable(route = RouteName.HomeRoute.MotionLayoutPage) {
                    MotionLayoutPage(navCtrl, RouteName.HomeRoute.MotionLayoutPage)
                }
                composable(route = RouteName.HomeRoute.NestedScrollPage) {
                    NestedScrollPage(navCtrl, RouteName.HomeRoute.NestedScrollPage)
                }
                composable(route = RouteName.HomeRoute.LeftScrollDeletePage) {
                    LeftScrollDeletePage(navCtrl, RouteName.HomeRoute.LeftScrollDeletePage)
                }

                composable(route = RouteName.HomeRoute.Goods3dImagePage) {
                    Goods3dImagePage(navCtrl, RouteName.HomeRoute.Goods3dImagePage)
                }
                composable(route = RouteName.HomeRoute.Goods3dImagePageTwo) {
                    Goods3dImagePageTwo(navCtrl, RouteName.HomeRoute.Goods3dImagePageTwo)
                }

                composable(route = RouteName.HomeRoute.Goods3dImagePageThree) {
                    Goods3dImagePageThree(navCtrl, RouteName.HomeRoute.Goods3dImagePageThree)
                }

                //TemplateRoute
                composable(route = RouteName.TemplateRoute.CameraPage) {
                    CameraPage(navCtrl, RouteName.TemplateRoute.CameraPage)
                }

                composable(route = RouteName.TemplateRoute.JingDongPage) {
                    JingDongPage(navCtrl, RouteName.TemplateRoute.JingDongPage)
                }

                composable(route = RouteName.TemplateRoute.MLKitPage) {
                    MLKitPage(navCtrl, RouteName.TemplateRoute.MLKitPage)
                }

                composable(route = RouteName.TemplateRoute.PermissionPage) {
                    PermissionPage(navCtrl, RouteName.TemplateRoute.PermissionPage)
                }

                composable(route = RouteName.TemplateRoute.SmartRefreshPage) {
                    SmartRefreshPage(navCtrl, RouteName.TemplateRoute.SmartRefreshPage)
                }

                composable(route = RouteName.TemplateRoute.WechatFriendsCirclePage) {
                    WechatFriendsCirclePage(
                        navCtrl,
                        RouteName.TemplateRoute.WechatFriendsCirclePage
                    )
                }
                composable(route = RouteName.TemplateRoute.ImageZoom) {
                    ImageZoom(navCtrl, RouteName.TemplateRoute.ImageZoom)
                }

                composable(route = RouteName.TemplateRoute.SealedClass) {
                    SealedClassListPage(navCtrl, RouteName.TemplateRoute.SealedClass)
                }

                composable(route = RouteName.TemplateRoute.DebouncedClickable) {
                    DebouncedClickable(navCtrl, RouteName.TemplateRoute.DebouncedClickable)
                }

                composable(route = RouteName.TemplateRoute.ColorPicker) {
                    ColorPickerPage(navCtrl, RouteName.TemplateRoute.ColorPicker)
                }

                composable(route = RouteName.TemplateRoute.Banner) {
                    BannerPage(navCtrl, RouteName.TemplateRoute.Banner)
                }
                composable(route = RouteName.TemplateRoute.StaggeredGridCompare) {
                    StaggeredGridCompare(navCtrl, RouteName.TemplateRoute.StaggeredGridCompare)
                }


                //widgets
                composable(route = RouteName.WidgetsRoute.KeyboardPage) {
                    KeyboardPage(navCtrl, RouteName.WidgetsRoute.KeyboardPage)
                }

                //Aim
                composable(route = RouteName.AimRoute.MarqueePage) {
                    MarqueePage(navCtrl, RouteName.AimRoute.MarqueePage)
                }

                composable(route = RouteName.AimRoute.Aim1) {
                    Aim1Page(navCtrl, RouteName.AimRoute.Aim1)
                }

                composable(route = RouteName.AimRoute.MoreText) {
                    MoreText(navCtrl, RouteName.AimRoute.MoreText)
                }


                //WebView
//                composable(route = RouteName.WEB_VIEW + "/{webData}",
//                    arguments = listOf(navArgument("webData") { type = NavType.StringType })
//                ) {
//                    val args = it.arguments?.getString("webData")?.fromJson<WebData>()
//                    if (args != null) {
//                        WebViewPage(webData = args, navCtrl = navCtrl)
//                    }
//                }

                //文章搜索页
//                composable(
//                    route = RouteName.ARTICLE_SEARCH + "/{id}",
//                    arguments = listOf(navArgument("id") { type = NavType.IntType })
//                ) {
//                    SearchPage(navCtrl, scaffoldState)
//                }
            }
        },
    )
}