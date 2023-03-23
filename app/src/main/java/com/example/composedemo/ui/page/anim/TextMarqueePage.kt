package com.example.composedemo.ui.page.anim

import android.content.res.Configuration
import androidx.compose.foundation.DefaultMarqueeDelayMillis
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar


@OptIn(ExperimentalFoundationApi::class, ExperimentalTextApi::class)
@Composable
fun TextMarqueePage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {

        val lanscapeConfig = LocalConfiguration.current.apply {
            orientation = Configuration.ORIENTATION_LANDSCAPE
        }
        CompositionLocalProvider(LocalConfiguration provides lanscapeConfig) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.basicMarquee(
                        iterations = Int.MAX_VALUE,
                        animationMode = MarqueeAnimationMode.Immediately,
                        delayMillis = 0,
                        initialDelayMillis = DefaultMarqueeDelayMillis,
                        spacing = MarqueeSpacing(200.dp),
//                    velocity = DefaultMarqueeVelocity
                        velocity = 300.dp,
                    ),
                    text = "Compose 1.4 跑马灯效果 还可以吧！",
                    fontSize = 200.sp,
                    minLines = 1,
                    maxLines = 1,
                    color = Color(0xFFE91E63),
                    style = TextStyle(drawStyle = Stroke(
                        width = 20f,
//                        miter = 50f,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Miter,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(20f,20f),5f)
                    ))
                )
            }
        }

    }

}