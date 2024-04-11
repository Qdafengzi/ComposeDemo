package com.example.composedemo.ui.page

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composedemo.ui.page.anim.Aim1Page
import com.example.composedemo.ui.page.anim.BannerAnimation
import com.example.composedemo.ui.page.anim.CanvasAnimation
import com.example.composedemo.ui.page.anim.LeftScrollSelect
import com.example.composedemo.ui.page.anim.MarqueePage
import com.example.composedemo.ui.page.anim.MoreText
import com.example.composedemo.ui.page.anim.MotionLayoutAnimation3
import com.example.composedemo.ui.page.anim.MotionLayoutAnimationOne
import com.example.composedemo.ui.page.anim.MotionLayoutAnimationTwo
import com.example.composedemo.ui.page.anim.PrintText
import com.example.composedemo.ui.page.anim.RecompositionExp
import com.example.composedemo.ui.page.anim.SkyAnimation
import com.example.composedemo.ui.page.anim.TextMarqueePage
import com.example.composedemo.ui.page.anim.TouchAnimationPage
import com.example.composedemo.ui.page.anim.TransformPage
import com.example.composedemo.ui.page.anim.VoteAnimation
import com.example.composedemo.ui.page.common.BottomNavBarView
import com.example.composedemo.ui.page.common.RouteName
import com.example.composedemo.ui.page.home.BezierPage
import com.example.composedemo.ui.page.home.BottomNavigationPage
import com.example.composedemo.ui.page.home.ButtonPage
import com.example.composedemo.ui.page.home.CanvasLinePage
import com.example.composedemo.ui.page.home.CanvasPage
import com.example.composedemo.ui.page.home.ClockPage
import com.example.composedemo.ui.page.home.ComplexPage
import com.example.composedemo.ui.page.home.ComposeWithXmlPage
import com.example.composedemo.ui.page.home.ConstraintLayoutPage
import com.example.composedemo.ui.page.home.ContactPage
import com.example.composedemo.ui.page.home.ContentScreensPage
import com.example.composedemo.ui.page.home.DraggableToolPalette
import com.example.composedemo.ui.page.home.FlowDataPage
import com.example.composedemo.ui.page.home.Goods3dImagePage
import com.example.composedemo.ui.page.home.Goods3dImagePageThree
import com.example.composedemo.ui.page.home.Goods3dImagePageTwo
import com.example.composedemo.ui.page.home.GridListPage
import com.example.composedemo.ui.page.home.ImageCardPage
import com.example.composedemo.ui.page.home.JavaPrettify
import com.example.composedemo.ui.page.home.LazyColumnPage
import com.example.composedemo.ui.page.home.LeftScrollDeletePage
import com.example.composedemo.ui.page.home.ListPage
import com.example.composedemo.ui.page.home.ModifierPage
import com.example.composedemo.ui.page.home.MotionLayoutPage
import com.example.composedemo.ui.page.home.NavigationPage
import com.example.composedemo.ui.page.home.NestedScrollPage
import com.example.composedemo.ui.page.home.NewUiStylePage
import com.example.composedemo.ui.page.home.PullRefreshPage
import com.example.composedemo.ui.page.home.ReCompositionPage
import com.example.composedemo.ui.page.home.RichTextPage
import com.example.composedemo.ui.page.home.RowAndColumn
import com.example.composedemo.ui.page.home.SaveHandlePage
import com.example.composedemo.ui.page.home.SnackBarPage
import com.example.composedemo.ui.page.home.StatePage
import com.example.composedemo.ui.page.home.TextPage
import com.example.composedemo.ui.page.home.TransformationPage
import com.example.composedemo.ui.page.home.WebViewPage
import com.example.composedemo.ui.page.home.XmlWithComposePage
import com.example.composedemo.ui.page.tab.AnimPage
import com.example.composedemo.ui.page.tab.HomePage
import com.example.composedemo.ui.page.tab.TemplatePage
import com.example.composedemo.ui.page.tab.WidgetsPage
import com.example.composedemo.ui.page.template.BannerPage
import com.example.composedemo.ui.page.template.BlurPage
import com.example.composedemo.ui.page.template.BottomBarPage
import com.example.composedemo.ui.page.template.CameraPage
import com.example.composedemo.ui.page.template.CameraXPage
import com.example.composedemo.ui.page.template.CanvasPageTwo
import com.example.composedemo.ui.page.template.ChannelPage
import com.example.composedemo.ui.page.template.ColorPickerPage
import com.example.composedemo.ui.page.template.DebouncedClickable
import com.example.composedemo.ui.page.template.GridViewPage
import com.example.composedemo.ui.page.template.HorizontalPagerPage
import com.example.composedemo.ui.page.template.ImageZoom
import com.example.composedemo.ui.page.template.JingDongPage
import com.example.composedemo.ui.page.template.MLKitPage
import com.example.composedemo.ui.page.template.NetErrorPage
import com.example.composedemo.ui.page.template.OutLineTextPage
import com.example.composedemo.ui.page.template.PermissionPage
import com.example.composedemo.ui.page.template.SealedClassListPage
import com.example.composedemo.ui.page.template.SmartRefreshPage
import com.example.composedemo.ui.page.template.StaggeredGridCompare
import com.example.composedemo.ui.page.template.StaggeredGridOffice
import com.example.composedemo.ui.page.template.SupportScreenSizePage
import com.example.composedemo.ui.page.template.WebViewPageNew
import com.example.composedemo.ui.page.template.WebViewPreLoading
import com.example.composedemo.ui.page.template.WechatFriendsCirclePage
import com.example.composedemo.ui.page.template.YoutubeListPage
import com.example.composedemo.ui.page.template.YoutubeRvPage
import com.example.composedemo.ui.page.widgets.ImagePaintPage
import com.example.composedemo.ui.page.widgets.KeyboardPage
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.InternalCoroutinesApi

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.R)
@InternalCoroutinesApi
@ExperimentalPermissionsApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun AppScaffold() {
    val navCtrl = rememberNavController()
    val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    androidx.compose.material3.Scaffold(
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
        content = {_->
            NavHost(
                modifier = Modifier.background(MaterialTheme.colors.background),
                navController = navCtrl,
                startDestination = RouteName.HOME
            ) {
                //首页
                composable(route = RouteName.HOME) {
                    HomePage(navCtrl)
                }

                composable(route = RouteName.WIDGETS) {
                    WidgetsPage(navCtrl)
                }
                composable(route = RouteName.Anim) {
                    AnimPage(navCtrl)
                }
                composable(route = RouteName.Template) {
                    TemplatePage(navCtrl)
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

                composable(route = RouteName.HomeRoute.SaveHandle) {
                    SaveHandlePage(navCtrl, RouteName.HomeRoute.SaveHandle)
                }

                composable(route = RouteName.HomeRoute.RefreshPage) {
                    PullRefreshPage(navCtrl, RouteName.HomeRoute.RefreshPage)
                }

                composable(route = RouteName.HomeRoute.Contact) {
                    ContactPage(navCtrl, RouteName.HomeRoute.Contact)
                }

                composable(route = RouteName.HomeRoute.Transformation) {
                    TransformationPage(navCtrl, RouteName.HomeRoute.Transformation)
                }

                composable(route = RouteName.HomeRoute.RichText) {
                    RichTextPage(navCtrl, RouteName.HomeRoute.RichText)
                }

                composable(route = RouteName.HomeRoute.DraggableToolPalette) {
                    DraggableToolPalette(navCtrl, RouteName.HomeRoute.DraggableToolPalette)
                }

                composable(route = RouteName.HomeRoute.RecompositionPage) {
                    ReCompositionPage(navCtrl, RouteName.HomeRoute.RecompositionPage)
                }

                composable(route = RouteName.HomeRoute.JavaPrettify) {
                    JavaPrettify(navCtrl, RouteName.HomeRoute.JavaPrettify)
                }

                composable(route = RouteName.HomeRoute.FlowData) {
                    FlowDataPage(navCtrl, RouteName.HomeRoute.FlowData)
                }


                //TemplateRoute
                composable(route = RouteName.TemplateRoute.CameraPage) {
                    CameraPage(navCtrl, RouteName.TemplateRoute.CameraPage)
                }

                composable(route = RouteName.TemplateRoute.CameraPageX) {
                    CameraXPage(navCtrl, RouteName.TemplateRoute.CameraPageX)
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

                composable(route = RouteName.TemplateRoute.ChannelPage) {
                    ChannelPage(navCtrl, RouteName.TemplateRoute.ChannelPage)
                }

                composable(route = RouteName.TemplateRoute.GridView) {
                    GridViewPage(navCtrl, RouteName.TemplateRoute.GridView)
                }

                composable(route = RouteName.TemplateRoute.NetError) {
                    NetErrorPage(navCtrl, RouteName.TemplateRoute.NetError)
                }

                composable(route = RouteName.TemplateRoute.SupportScreenSize) {
                    SupportScreenSizePage(navCtrl, RouteName.TemplateRoute.SupportScreenSize)
                }
                composable(route = RouteName.TemplateRoute.CanvasPageTwo) {
                    CanvasPageTwo(navCtrl, RouteName.TemplateRoute.CanvasPageTwo)

                }

                composable(route = RouteName.TemplateRoute.WebView) {
                    WebViewPageNew(navCtrl, RouteName.TemplateRoute.WebView)
                }

                composable(route = RouteName.TemplateRoute.StaggeredGrid_office) {
                    StaggeredGridOffice(navCtrl, RouteName.TemplateRoute.StaggeredGrid_office)
                }

                composable(route = RouteName.TemplateRoute.Youtube_page) {
                    YoutubeListPage(navCtrl, RouteName.TemplateRoute.Youtube_page)
                }

                composable(route = RouteName.TemplateRoute.Youtube_Rv_page) {
                    YoutubeRvPage(navCtrl, RouteName.TemplateRoute.Youtube_Rv_page)
                }

                composable(route = RouteName.TemplateRoute.BlurPage) {
                    BlurPage(navCtrl, RouteName.TemplateRoute.BlurPage)
                }
                composable(route = RouteName.TemplateRoute.WebViewPreLoading) {
                    WebViewPreLoading(navCtrl, RouteName.TemplateRoute.WebViewPreLoading)
                }

                composable(route = RouteName.TemplateRoute.HorizontalPagerPage) {
                    HorizontalPagerPage(navCtrl, RouteName.TemplateRoute.HorizontalPagerPage)
                }

                composable(route = RouteName.TemplateRoute.OutLineText) {
                    OutLineTextPage(navCtrl, RouteName.TemplateRoute.OutLineText)
                }

                composable(route = RouteName.TemplateRoute.BottomBar) {
                    BottomBarPage(navCtrl, RouteName.TemplateRoute.BottomBar)
                }


                //widgets
                composable(route = RouteName.WidgetsRoute.KeyboardPage) {
                    KeyboardPage(navCtrl, RouteName.WidgetsRoute.KeyboardPage)
                }

                composable(route = RouteName.WidgetsRoute.ImagePaintPage) {
                    ImagePaintPage(navCtrl, RouteName.WidgetsRoute.ImagePaintPage)
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

                composable(route = RouteName.AimRoute.TouchAnimation) {
                    TouchAnimationPage(navCtrl, RouteName.AimRoute.TouchAnimation)
                }

                composable(route = RouteName.AimRoute.TransformPage) {
                    TransformPage(navCtrl, RouteName.AimRoute.TransformPage)
                }
                composable(route = RouteName.AimRoute.VoteAnimation) {
                    VoteAnimation(navCtrl, RouteName.AimRoute.VoteAnimation)
                }

                composable(route = RouteName.AimRoute.MotionLayoutAnimation) {
                    MotionLayoutAnimationOne(navCtrl, RouteName.AimRoute.MotionLayoutAnimation)
                }

                composable(route = RouteName.AimRoute.MotionLayoutAnimationTwo) {
                    MotionLayoutAnimationTwo(navCtrl, RouteName.AimRoute.MotionLayoutAnimationTwo)
                }

                composable(route = RouteName.AimRoute.MotionLayoutAnimation3) {
                    MotionLayoutAnimation3(navCtrl, RouteName.AimRoute.MotionLayoutAnimation3)
                }

                composable(route = RouteName.AimRoute.Recomposition) {
                    RecompositionExp(navCtrl, RouteName.AimRoute.Recomposition)
                }

                composable(route = RouteName.AimRoute.CanvasAnimation) {
                    CanvasAnimation(navCtrl, RouteName.AimRoute.CanvasAnimation)
                }

                composable(route = RouteName.AimRoute.SkyAnimation) {
                    SkyAnimation(navCtrl, RouteName.AimRoute.SkyAnimation)
                }

                composable(route = RouteName.AimRoute.BannerAnimation) {
                    BannerAnimation(navCtrl, RouteName.AimRoute.BannerAnimation)
                }

                composable(route = RouteName.AimRoute.TextMarqueeAnimation) {
                    TextMarqueePage(navCtrl, RouteName.AimRoute.TextMarqueeAnimation)
                }

                composable(route = RouteName.AimRoute.PrintText) {
                    PrintText(navCtrl, RouteName.AimRoute.PrintText)
                }

                composable(route = RouteName.AimRoute.LeftScrollSelect) {
                    LeftScrollSelect(navCtrl, RouteName.AimRoute.LeftScrollSelect)
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