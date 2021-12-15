package com.example.composedemo.ui.page.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar


@Composable
fun CanvasLinePage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier
                .height(300.dp)
                .background(color = Color.Green))

            val pathEffect = PathEffect.dashPathEffect(floatArrayOf(30f, 10f), 10f)
            Canvas(
                Modifier
                    .width(10.dp)
                    .height(100.dp)) {

                drawLine(
                    color = Color.Red,
                    start = Offset(size.width / 2, 0f),
                    end = Offset(size.width / 2, size.height),
                    pathEffect = pathEffect,
                    strokeWidth = 4f,
                )
            }
        }
    }
}