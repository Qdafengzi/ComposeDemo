package com.example.composedemo.ui.page.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.theme.Co_green
import com.example.composedemo.ui.widget.CommonToolbar


@Composable
fun ButtonPage(navCtrl: NavHostController, title: String) {

    CommonToolbar(navCtrl,title) {
        Column() {
            //是否可以点击
            val clickAble = remember {
                mutableStateOf(
                    true
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Button(
                    onClick = {
                        clickAble.value = false
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Co_green,
                        //不可以点击的颜色
                        disabledBackgroundColor = Color.Gray
                    ),
                    //不可以点击控制
                    enabled = clickAble.value
                ) {
                    Text(
                        text = "Click me",
                        modifier = Modifier
                            .fillMaxWidth(),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                    )
                }
            }
        }
    }
}