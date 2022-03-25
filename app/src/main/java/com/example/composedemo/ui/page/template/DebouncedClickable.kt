package com.example.composedemo.ui.page.template

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import kotlinx.coroutines.delay

/**
 * Created by finn on 2022/3/25
 */

@Composable
fun DebouncedClickable(navCtrl: NavHostController, title: String) {

    val clicked = remember {
        mutableStateOf(false)
    }

    CommonToolbar(navCtrl, title) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = if (clicked.value) Color.Blue else Color.Red),
        ) {
            Box(modifier = Modifier.fillMaxSize().debouncedClickable(
                enabled = true,
                onClick = {
                    clicked.value = !clicked.value
                },
//               delay = 10L,
               delay = 1000L,
            )) {
                Text(
                    text = if (clicked.value) "蓝色" else "红色",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

fun Modifier.debouncedClickable(enabled: Boolean, onClick: () -> Unit, delay: Long = 300) =
    composed {
        var clicked by remember {
            mutableStateOf(!enabled)
        }
        LaunchedEffect(key1 = clicked, block = {
            if (clicked) {
                delay(delay)
                clicked = !clicked
            }
        })

        Modifier.clickable(if (enabled) !clicked else false) {
            clicked = !clicked
            onClick()
        }
    }