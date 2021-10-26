package com.example.composedemo.ui.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.dp
import com.example.composedemo.R

class NewUiStyleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewUiStyle()
        }
    }

    @Composable
    fun NewUiStyle() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Box(
                modifier = Modifier
                    .size(300.dp, 200.dp)
                    .padding(20.dp)
                    .border(width = 10.dp, color = Color.Black, shape = RectangleShape)
//                    .background(brush = Brush.linearGradient(colors = listOf(Color.Black)),shape = RectangleShape)
                    .background(color = Color.White, shape = CircleShape)
                    .shadow(elevation = 20.dp, shape = RoundedCornerShape(20.dp))
//                    .border(width = 20.dp,color = Color.Gray,shape = RoundedCornerShape(0.5f))
            ) {

            }
        }
    }
}