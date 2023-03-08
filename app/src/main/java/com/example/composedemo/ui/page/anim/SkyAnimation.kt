package com.example.composedemo.ui.page.anim

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import kotlinx.coroutines.delay


@Composable
fun SkyAnimation(navCtrl: NavHostController, title: String) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp

    val width = with(LocalDensity.current) {
        screenWidth.dp.toPx()
    }
    val height = with(LocalDensity.current) {
        screenHeight.dp.toPx()
    }

    val listPoints = remember {
        mutableListOf<Offset>()
    }

    LaunchedEffect(key1 = Unit, block = {
        repeat(300) {
            val x = (0..width.toInt()).random()
            val y = (0..height.toInt()).random()
            listPoints.add(Offset(x.toFloat(), y.toFloat()))
        }
    })

    CommonToolbar(navCtrl, title) {
        Content(listPoints)
    }
}

@Composable
private fun Content(listPoints: MutableList<Offset>) {
    var time by remember {
        mutableStateOf(System.currentTimeMillis())
    }
    val sizeAnimate by animateIntAsState(
        if (time % 2 == 0L) 2 else 4, animationSpec = tween(1000), label = ""
    )

    LaunchedEffect(key1 = sizeAnimate, block = {
        while (true) {
            delay(1000)
            time = System.currentTimeMillis()
        }
    })

    Spacer(modifier = Modifier
        .fillMaxSize()
        .drawBehind {
            drawRect(color = Color(0xFF000000))
            listPoints.forEachIndexed { index, offset ->
                val list = listOf(offset)
                if (index % 4 == 0) {
                    drawPoints(
                        points = list,
                        color = Color.White,
                        strokeWidth = sizeAnimate.dp.toPx(),
                        pointMode = PointMode.Points,
                        cap = StrokeCap.Round,
                    )
                } else {
                    drawPoints(
                        points = list,
                        color = Color.White,
                        strokeWidth = 1.dp.toPx(),
                        pointMode = PointMode.Points,
                        cap = StrokeCap.Round,
                    )
                }
            }
        })
}