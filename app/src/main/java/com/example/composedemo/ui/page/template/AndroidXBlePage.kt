package com.example.composedemo.ui.page.template

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.composedemo.state.BleViewModel
import com.example.composedemo.ui.widget.CommonToolbar
import kotlinx.coroutines.launch


/**
 * Created by finn on 2022/5/6
 */
@Composable
fun AndroidXBlePage(navCtrl: NavHostController, title: String) {
    val context = LocalContext.current
    val viewModel = BleViewModel(context)
    val scope = rememberCoroutineScope()

    CommonToolbar(navCtrl, title) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text("打开状态：${if (viewModel.blueIsOpen) "已开启" else "关闭"}")
            Button(onClick = {
                scope.launch {
                    viewModel.scan()
                }
            }) {
                Text("扫描")
            }
            LazyColumn {
                viewModel.deviceList.forEach {
                    item {
                        Text(text = it,color = Color.Red)
                    }
                }
            }

        }
    }
}