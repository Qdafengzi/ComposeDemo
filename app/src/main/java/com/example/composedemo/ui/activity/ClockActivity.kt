package com.example.composedemo.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

class ClockActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Clock()
        }
    }

    @Composable
    fun Clock() {
        val hour = remember {
            mutableStateOf(0f)
        }
        val minute = remember {
            mutableStateOf(0f)
        }
        val second = remember {
            mutableStateOf(0f)
        }

        LaunchedEffect(key1 = "") {
            while (true) {
                val cal = Calendar.getInstance()
                hour.value = cal.get(Calendar.HOUR).toFloat()
                minute.value = cal.get(Calendar.MINUTE).toFloat()
                second.value = cal.get(Calendar.SECOND).toFloat()
                delay(1000)
                Log.d(
                    "----->>>",
                    "${cal.hashCode()}   ${hour.value}  ${minute.value}  ${second.value}"
                )
            }
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            val diameter = 200.dp
            val color1 = Color.Black

            Canvas(
                modifier = Modifier
                    .size(diameter, diameter)
            ) {

                val outCircleR = size.width / 2
                val innerCircleR = (size.width / 2 * 0.8).toFloat()
                //最里面的圆心
                drawCircle(color = color1, radius = size.width * 0.02f)
                //外部大圆
                drawCircle(color = color1, radius = outCircleR, style = Stroke(4f))
                //里面小圆
                drawCircle(
                    color = color1,
                    radius = innerCircleR,
                    style = Stroke(2f)
                )
                translate(size.width / 2, size.height / 2) {
                    //画刻度
                    for (index in 0..330 step 30) {
                        drawLine(
                            color = color1,
                            start = Offset(
                                (innerCircleR * 1.05f * cos(index * Math.PI / 180)).toFloat(),
                                (innerCircleR * 1.05f * sin(index * Math.PI / 180)).toFloat()
                            ),
                            end = Offset(
                                (outCircleR * 0.95f * cos(index * Math.PI / 180)).toFloat(),
                                (outCircleR * 0.95f * sin(index * Math.PI / 180)).toFloat()
                            ),
                            strokeWidth = 8f,
                            cap = StrokeCap.Round
                        )
                    }

                    //画时针
                    //hour的角度 -3 + 分钟  / 12   * 360
                    val hourLength = innerCircleR * 0.6f
                    val hourAngle = (hour.value.plus(minute.value.div(60)).minus(3)).div(12)*360
                    drawLine(
                        color = color1,
                        start = Offset.Zero,
                        end = Offset(
                            (hourLength * cos(hourAngle * Math.PI / 180)).toFloat(),
                            (hourLength * sin(hourAngle * Math.PI / 180)).toFloat()
                        ),
                        strokeWidth = 8f,
                        cap = StrokeCap.Round
                    )
                    //画分针
                    val minuteLength = innerCircleR * 0.9f
                    val minuteAngle = (minute.value.plus(second.value.div(60)).minus(15)).div(60)*360
                    drawLine(
                        color = color1,
                        start = Offset.Zero,
                        end = Offset(
                            (minuteLength * cos(minuteAngle * Math.PI / 180)).toFloat(),
                            (minuteLength * sin(minuteAngle * Math.PI / 180)).toFloat()
                        ),
                        strokeWidth = 6f,
                        cap = StrokeCap.Round
                    )
                    //画秒针
                    val secondLength = outCircleR * 0.9f
                    val secondAngle = second.value.div(60)*360
                    drawLine(
                        color = color1,
                        start = Offset.Zero,
                        end = Offset(
                            (secondLength * cos(secondAngle * Math.PI / 180)).toFloat(),
                            (secondLength * sin(secondAngle * Math.PI / 180)).toFloat()
                        ),
                        strokeWidth = 2f,
                        cap = StrokeCap.Round
                    )
                }
            }
        }
    }
}