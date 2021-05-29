package com.example.composedemo.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

class StateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
}