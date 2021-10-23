package com.example.composedemo.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

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
                    .background(color = Color.White, shape = CircleShape)
                    .shadow(elevation = 20.dp,shape = RoundedCornerShape(20.dp))
//                    .border(width = 20.dp,color = Color.Gray,shape = RoundedCornerShape(0.5f))
            ) {

            }
        }
    }

    @Stable
    val MyShape = object :Shape{
        override fun createOutline(
            size: Size,
            layoutDirection: LayoutDirection,
            density: Density
        ): Outline {
           return  Outline.Rectangle(size.toRect())
        }

    }

    val MyRectangleShape: Shape = object : Shape {
        override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density) =
            Outline.Rectangle(size.toRect())

        override fun toString(): String = "RectangleShape"
    }
}