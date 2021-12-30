package com.example.composedemo.ui.page.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.R
import com.example.composedemo.ui.widget.CommonToolbar
import kotlinx.coroutines.coroutineScope
import kotlin.math.abs

@SuppressLint("ClickableViewAccessibility")
@Composable
fun Goods3dImagePageThree(navCtrl: NavHostController, title: String) {
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
    val imageId = remember {
        mutableStateOf(R.mipmap.img_1)
    }

    val oneInterval = with(LocalDensity.current) { (width.dp / arr.size).toPx() }
    var position = 0
    var x2 = 0f
    var x1 = 0f

    CommonToolbar(navCtrl, title) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .pointerInput(Unit) {
                        while (true) {
                            // down事件
                            val downPointerInputChange = awaitPointerEventScope {
                                awaitFirstDown()
                            }
                            x1 = downPointerInputChange.position.x
                            // touch Move事件
                            // 滑动的时候，box随着手指的移动去移动
                            awaitPointerEventScope {
                                drag(downPointerInputChange.id, onDrag = {
                                    x2 = it.position.x
                                    val distance: Float = x2 - x1
                                    if (distance > 0) {
                                        if (distance > oneInterval) {
                                            position += 1
                                            if (position > arr.size-1) {
                                                position = 0
                                            }
                                            x1 = x2
//                                            imageView.setImageResource(arr[position])
                                            imageId.value = arr[position]
                                        }
                                    } else {
                                        if (abs(distance) > oneInterval) {
                                            position -= 1
                                            if (position < 0) {
                                                position = arr.size-1
                                            }
                                            x1 = x2
//                                            imageView.setImageResource(arr[position])
                                            imageId.value = arr[position]
                                        }
                                    }



                                })
                            }

                            // 在手指弹起的时候，才通过动画的形式，回到原点的位置
                            val dragUpOrCancelPointerInputChange = awaitPointerEventScope {
                                awaitDragOrCancellation(downPointerInputChange.id)
                            }
                            // 等于空，说明已经抬起
                            if (dragUpOrCancelPointerInputChange == null) {
                                x1 = 0f
                            }
                        }
                    },
                painter = painterResource(id = imageId.value), contentDescription = null
            )
        }
    }
}