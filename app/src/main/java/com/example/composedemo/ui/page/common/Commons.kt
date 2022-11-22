package com.example.composedemo.ui.page.common

import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composedemo.ui.theme.AppTheme


@Composable
fun BottomNavBarView(navCtrl: NavHostController) {
    val bottomNavList = listOf(
        BottomNavRoute.Home,
        BottomNavRoute.Widgets,
        BottomNavRoute.Anim,
        BottomNavRoute.Template
    )
    BottomNavigation {
        val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomNavList.forEach { screen ->
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.routeName } == true
            BottomNavigationItem(
                modifier = Modifier.background(AppTheme.colors.themeUi),
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = null,
                        tint = if(isSelected) Color.White else  Color(0xA8F5F8F5)
                    )
                },
                label = {
                    Text(
                        text = stringResource(screen.stringId),
                        color = if (isSelected) Color.White else  Color(0xA8F5F8F5),
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                    )
                },
                selected = isSelected,
                onClick = {
                    println("BottomNavView当前路由 ===> ${currentDestination?.hierarchy?.toList()}")
                    println("当前路由栈 ===> ${navCtrl.graph.nodes}")
                    if (currentDestination?.route != screen.routeName) {
                        navCtrl.navigate(screen.routeName) {
                            popUpTo(navCtrl.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                })
        }
    }
}

