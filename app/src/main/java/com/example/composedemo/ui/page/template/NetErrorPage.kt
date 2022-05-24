package com.example.composedemo.ui.page.template

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.composedemo.R
import com.example.composedemo.ui.page.template.net.ConnectionState
import com.example.composedemo.ui.page.template.net.connectivityState
import com.example.composedemo.ui.widget.CommonToolbar
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by finn on 2022/5/6
 */
@Composable
fun NetErrorPage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        NetScreen()
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun NetScreen() {
    val connection by connectivityState()
    Column(
        Modifier.fillMaxSize(),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        if (connection == ConnectionState.Available) {
            Text(
                "内容",
                fontSize = 25.sp,
                fontWeight = FontWeight.W600,
                color = Color.DarkGray
            )
        } else {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.net_error))
            val progress by animateLottieCompositionAsState(composition)
            LottieAnimation(
                composition,
                progress,
            )
        }
    }
}
