package com.example.composedemo.ui.page.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.theme.AppTheme
import kotlin.random.Random


@Composable
fun StatePage(navCtrl: NavHostController, title: String) {
    Scaffold(topBar = {
        TopAppBar(backgroundColor = AppTheme.colors.toolbarColor) {
            Icon(
                modifier = Modifier.clickable {
                    navCtrl.popBackStack()
                },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = AppTheme.colors.mainColor
            )
            Text(
                modifier = Modifier.padding(20.dp),
                text = title,
                color = AppTheme.colors.mainColor
            )
        }
    }) {
        Column() {
            Column(modifier = Modifier.fillMaxSize()) {
                //记住颜色状态不会重复赋值
                val color = remember {
                    mutableStateOf(
                        Color.Gray
                    )
                }
                ClickBox(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                ) {
                    //产生的Color颜色回调到这里 给color赋值
                    color.value = it
                }
                Box(
                    modifier = Modifier
                        .background(color = color.value)
                        .weight(1f)
                        .fillMaxSize()
                ) {

                }
            }
        }
    }
}

@Composable
fun ClickBox(modifier: Modifier = Modifier, colorUpdate: (Color) -> Unit) {
    Box(
        modifier = modifier
            .background(color = Color.Blue)
            .clickable {
                //点击后产生随机颜色
                colorUpdate(
                    Color(
                        Random.nextFloat(),
                        Random.nextFloat(),
                        Random.nextFloat(),
                        1f
                    )
                )
            }
    ) {

    }
}