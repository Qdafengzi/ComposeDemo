package com.example.composedemo.ui.page.template

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.page.widgets.Dimensions
import com.example.composedemo.ui.widget.CommonToolbar
import com.example.composedemo.utils.XLogger

/**
 * Created by finn on 2022/5/24
 */
@Composable
fun SupportScreenSizePage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Card(
                modifier = Modifier
                    .width(Dimensions.width(value = 50f).dp)
                    .height(Dimensions.height(value = 50f).dp),
                backgroundColor = Color.DarkGray
            ) {

                XLogger.d("fontSize----->${Dimensions.fontSize(value = 14f)}")
                Text(
                    text = "font size",
                    fontSize = Dimensions.fontSize(value = 14f).sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
            }
        }
    }
}