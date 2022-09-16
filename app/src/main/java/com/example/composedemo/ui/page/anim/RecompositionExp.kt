package com.example.composedemo.ui.page.anim

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import com.example.composedemo.ui.widget.recomposeHighlighter

/**
 * Created by finn on 2022/9/16
 */

@Composable
fun RecompositionExp(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        Column(modifier = Modifier.fillMaxSize()) {
            val count = remember {
                mutableStateOf(0)
            }

            Text(modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .fillMaxWidth()
                .height(100.dp)
                .recomposeHighlighter(), text = "${count.value}", color = Color.Black)

            Button(onClick = {
                count.value = count.value + 1
            }) {
                Text(text = "Add", modifier = Modifier.fillMaxWidth(), color = Color.White)
            }
        }
    }
}