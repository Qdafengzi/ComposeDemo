package com.example.composedemo.ui.page.common

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.composedemo.R

sealed class BottomNavRoute(
    var routeName: String,
    @StringRes var stringId: Int,
    var icon: ImageVector
) {
    object Home: BottomNavRoute(RouteName.HOME, R.string.home, Icons.Default.Home)
    object Widgets: BottomNavRoute(RouteName.WIDGETS, R.string.widgets, Icons.Default.Settings)
    object Anim: BottomNavRoute(RouteName.Anim, R.string.anim, Icons.Default.PlayArrow)
    object Template: BottomNavRoute(RouteName.Template, R.string.template, Icons.Default.Done)
}