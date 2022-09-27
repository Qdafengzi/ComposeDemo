package com.example.composedemo.ui.page.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.composedemo.state.SaveHandleViewModel
import com.example.composedemo.ui.widget.CommonToolbar
import com.example.composedemo.utils.XLogger

/**
 * 没起作用 不知道是不是版本不对？？ 懒的排除其他版本的依赖了 等稳定后再测试
 */
@Composable
fun SaveHandlePage(navCtrl: NavHostController, title: String) {
    val viewModel: SaveHandleViewModel = viewModel()

    val color = viewModel.color.collectAsState().value
    XLogger.d("color--------->${color}")
    CommonToolbar(navCtrl, title) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color(color))
            .clickable {
                XLogger.d("-------aaaaa")
                viewModel.changeColor()
            }
        ) {

        }
    }
}