package com.example.composedemo.ui.page.template

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import com.example.composedemo.utils.XLogger
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

/**
 * Created by finn on 2022/5/6
 */
@Composable
fun ChannelPage(navCtrl: NavHostController, title: String) {
    val scope = rememberCoroutineScope()
    CommonToolbar(navCtrl, title) {
        val channel = Channel<Int>()
        var num = 0

        Button(onClick = {
            num++
            scope.launch {
                channel.send(num)
                //接收
                val receiveNum =  channel.receive()
                XLogger.d("----->$receiveNum")
            }
        }) {
            Text(text = "Add")
        }
        Text(text = "num is $num")
    }
}