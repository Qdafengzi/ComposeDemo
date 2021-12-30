package com.example.composedemo.ui.page.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.appcompat.widget.AppCompatImageView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.composedemo.R
import com.example.composedemo.ui.widget.CommonToolbar
import kotlin.math.abs

@SuppressLint("ClickableViewAccessibility")
@Composable
fun Goods3dImagePageTwo(navCtrl: NavHostController, title: String) {
    val width = LocalConfiguration.current.screenWidthDp

    val arr = intArrayOf(
        R.mipmap.img_1,
        R.mipmap.img_2,
        R.mipmap.img_3,
        R.mipmap.img_4,
        R.mipmap.img_5,
        R.mipmap.img_6,
        R.mipmap.img_7,
        R.mipmap.img_8,
        R.mipmap.img_9,
        R.mipmap.img_10,
        R.mipmap.img_11,
        R.mipmap.img_12,
        R.mipmap.img_13,
        R.mipmap.img_14,
        R.mipmap.img_15,
        R.mipmap.img_16,
        R.mipmap.img_17,
        R.mipmap.img_18,
        R.mipmap.img_19,
        R.mipmap.img_20,
        R.mipmap.img_21,
        R.mipmap.img_22,
        R.mipmap.img_23,
        R.mipmap.img_24,
        R.mipmap.img_25,
        R.mipmap.img_26,
        R.mipmap.img_27,
        R.mipmap.img_28,
        R.mipmap.img_29,
        R.mipmap.img_30,
        R.mipmap.img_31,
        R.mipmap.img_32,
        R.mipmap.img_33,
        R.mipmap.img_34,
        R.mipmap.img_35,
        R.mipmap.img_36,
        R.mipmap.img_37,
        R.mipmap.img_38
    )
    val oneInterval = with(LocalDensity.current) { (width.dp / arr.size).toPx() }
    var position = 0
    var x2 = 0f
    var x1 = 0f
    CommonToolbar(navCtrl, title) {
        Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                factory = { ctx ->
                    val view = LayoutInflater.from(ctx).inflate(R.layout.activity_3d_image, null)
                    val imageView = view.findViewById<AppCompatImageView>(R.id.mImage)
                    imageView.setOnTouchListener(object : OnTouchListener {
                        override fun onTouch(v: View, event: MotionEvent): Boolean {
                            event.x
                            if (event.action == MotionEvent.ACTION_DOWN) {
                                x1 = event.x
                            } else if (event.action == MotionEvent.ACTION_UP) {
                                x1 = 0f
                            } else if (event.action == MotionEvent.ACTION_MOVE) {
                                x2 = event.x
                                val distance: Float = x2 - x1
                                if (distance > 0) {
                                    if (distance > oneInterval) {
                                        position += 1
                                        if (position > arr.size-1) {
                                            position = 0
                                        }
                                        x1 = x2

                                        imageView.setImageResource(arr[position])
                                    }
                                } else {
                                    if (abs(distance) > oneInterval) {
                                        position -= 1
                                        if (position < 0) {
                                            position = arr.size-1
                                        }
                                        x1 = x2
                                        imageView.setImageResource(arr[position])
                                    }
                                }
                            }
                            return true
                        }
                    })
                    view
                },
            )
        }
    }
}