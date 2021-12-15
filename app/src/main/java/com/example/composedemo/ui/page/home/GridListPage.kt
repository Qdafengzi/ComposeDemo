package com.example.composedemo.ui.page.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import com.example.composedemo.ui.widget.VerticalGrid


@Composable
fun GridListPage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        VerticalGrid(columns = 3) {
            for (index in 1..100) {
                Box(modifier = Modifier.background(color = Color.Green)) {
                    Text(
                        text = "Text $index", modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    )
                }
            }
        }
    }
}