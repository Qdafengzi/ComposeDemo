package com.example.composedemo.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ModifierActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .background(Color.Green)
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth()
//                .width(600.dp)//如果宽度比屏幕还宽 则按照最大屏幕宽度来设置大小
//                .requiredWidth(600.dp)//会按照实际值来设置大小
                    .padding(16.dp)
                    .border(width = 1.dp, color = Color.Gray, shape = RectangleShape)
                    .padding(5.dp)
                    .border(width = 5.dp, color = Color.Red)
                    .padding(10.dp)
                    .border(width = 10.dp, color = Color.Magenta)

            ) {
                //向下和右侧 均偏移50dp
                Text(text = "hello", modifier = Modifier.offset(50.dp, 50.dp))
                //留空间高度：50dp
                Spacer(modifier = Modifier.height(50.dp))
                Text(text = "Compose")
                Text(
                    text = "World", modifier = Modifier
                        .border(1.dp, color = Color.Cyan)
                        .padding(10.dp)
                        .border(2.dp, color = Color.Blue)
                        .padding(20.dp)
                        .border(3.dp, color = Color.Red)
                )

                Text(text = "Click",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(20.dp)
                        .clickable {
                            Log.d("------->", "click ok")
                        })
            }

        }
    }
}