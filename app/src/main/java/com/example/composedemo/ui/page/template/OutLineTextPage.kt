package com.example.composedemo.ui.page.template

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar

@Composable
fun OutLineTextPage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "Magic Compose",
                fontSize = 64.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black,
                style = TextStyle(
                    drawStyle = Stroke(
                        width = 32F
                    )
                )
            )

            Text(
                text = "Magic Compose",
                fontSize = 64.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
        }
    }
}

