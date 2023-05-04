package com.example.composedemo.ui.page.template

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composedemo.ui.page.template.viewmodel.LockViewModel
import com.example.composedemo.ui.widget.CommonToolbar


@Composable
fun KotlinLock(
    navCtrl: NavHostController,
    title: String,
    viewModel: LockViewModel = hiltViewModel()
) {
    CommonToolbar(navCtrl, title) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "${viewModel.num.value}",
            color = Color.Black,
            fontSize = 20.sp
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)) {
            Button(modifier = Modifier.weight(1f), onClick = {
                viewModel.start()
            }) {
                Text(text = "启动", color = Color.White, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.width(100.dp))
            Button(modifier = Modifier.weight(1f), onClick = {
                viewModel.stop()
            }) {
                Text(text = "暂停", color = Color.White, fontSize = 14.sp)
            }
        }
    }
}