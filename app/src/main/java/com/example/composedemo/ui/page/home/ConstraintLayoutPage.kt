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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.example.composedemo.ui.theme.AppTheme


@Composable
fun ConstraintLayoutPage(navCtrl: NavHostController, title: String) {
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
            val constraints = ConstraintSet {
                val greenBox = createRefFor("greenBox")
                val redBox = createRefFor("redBox")
                //分界线 50%的位置
                val guideline = createGuidelineFromTop(0.5f)

                //绿色块的约束位置
                constrain(greenBox) {
                    top.linkTo(guideline)//顶部连接分界线
                    start.linkTo(parent.start)
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }

                //红色块的约束位置
                constrain(redBox) {
                    top.linkTo(parent.top)
                    start.linkTo(greenBox.end)
//                    end.linkTo(parent.end)//红色会在剩余的空间中居中
//                    width = Dimension.fillToConstraints//填充满
                    width = Dimension.value(100.dp)
                    height = Dimension.value(100.dp)
                }
                createHorizontalChain(greenBox, redBox)//两个均匀分布
            }

            ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .background(color = Color.Green)
                        .layoutId("greenBox")
                )
                Box(
                    modifier = Modifier
                        .background(color = Color.Red)
                        .layoutId("redBox")
                )
            }
        }
    }
}