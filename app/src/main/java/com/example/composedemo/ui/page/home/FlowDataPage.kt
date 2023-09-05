package com.example.composedemo.ui.page.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composedemo.state.FlowDataViewModel
import com.example.composedemo.ui.widget.CommonToolbar


@Composable
fun FlowDataPage(
    navCtrl: NavHostController,
    title: String,
    viewModel: FlowDataViewModel = hiltViewModel()
) {
    val fileName = viewModel.fileName.collectAsState().value
    CommonToolbar(navCtrl, title) {
        Text(text = fileName, modifier = Modifier.fillMaxWidth())

        Button(modifier = Modifier.fillMaxWidth().padding(top = 50.dp), onClick = {
            viewModel.updateData()
        }) {
            Text(text = "获取数据", modifier = Modifier.fillMaxWidth())
        }

    }
}