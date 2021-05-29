package com.example.composedemo.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.composedemo.R

class FontActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //创建字体
        val fontFamily = FontFamily(
            Font(R.font.opensans_bold, FontWeight.Bold),
            Font(R.font.opensans_light, FontWeight.Thin),
            Font(R.font.opensans_semibold, FontWeight.Normal),
        )

        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF101010))
            ) {
                Text(
//                    text = "Android Jetpack Compose",
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Red,
                                fontSize = 24.sp
                            )
                        ) {
                            append("A")
                        }
                        append("ndroid ")
                        withStyle(
                            style = SpanStyle(
                                color = Color.Red,
                                fontSize = 24.sp
                            )
                        ) {
                            append("J")
                        }
                        append("etpack ")
                        withStyle(
                            style = SpanStyle(
                                color = Color.Red,
                                fontSize = 24.sp
                            )
                        ) {
                            append("C")
                        }
                        append("ompose")

                    },
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
//                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center,
//                    textDecoration = TextDecoration.LineThrough,
//                    textDecoration = TextDecoration.Underline,
                )
            }
        }
    }
}