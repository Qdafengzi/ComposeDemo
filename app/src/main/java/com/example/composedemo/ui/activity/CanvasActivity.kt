package com.example.composedemo.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class CanvasActivity : AppCompatActivity() {

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            DragTest()
            DragGestureDemo()
//            TransformGestureDemo()
        }
    }


    @SuppressLint("RememberReturnType")
    @Preview
    @Composable
    fun DragTest(){
        val cacheOffset = remember() {
            mutableStateOf(Offset.Zero)
        }
        val offsetAnimatable = remember { Animatable(Offset(0f, 0f), Offset.VectorConverter) }
        Box(
            Modifier
                .pointerInput(Unit) {
                    coroutineScope {
                        while (true) {
                            // down事件
                            val downPointerInputChange = awaitPointerEventScope {
                                awaitFirstDown()
                            }
                            offsetAnimatable.stop()
                            // 如果位置不在手指按下的位置，先动画的形式过度到手指按下的位置
                            if (cacheOffset.value.x != downPointerInputChange.position.x
                                && cacheOffset.value.y != downPointerInputChange.position.y
                            ) {
                                launch {
                                    offsetAnimatable.animateTo(downPointerInputChange.position)
                                    cacheOffset.value = downPointerInputChange.position
                                }
                            }

                            // touch Move事件
                            // 滑动的时候，box随着手指的移动去移动
                            awaitPointerEventScope {
                                drag(downPointerInputChange.id, onDrag = {
                                    launch {
                                        offsetAnimatable.snapTo(it.position)
                                    }
                                    cacheOffset.value = it.position
                                })
                            }

                            // 在手指弹起的时候，才通过动画的形式，回到原点的位置
                            val dragUpOrCancelPointerInputChange = awaitPointerEventScope {
                                awaitDragOrCancellation(downPointerInputChange.id)
                            }
                            // 等于空，说明已经抬起
                            if(dragUpOrCancelPointerInputChange==null){
                                launch {
                                    val result = offsetAnimatable.animateTo(Offset.Zero)
                                    cacheOffset.value = Offset.Zero
                                }
                            }
                        }
                    }
                }
                .fillMaxSize()
        ){
            Box(modifier = Modifier.offset{ IntOffset(offsetAnimatable.value.x.roundToInt(), offsetAnimatable.value.y.roundToInt()) }.size(50.dp).background(Color.Blue))
        }
    }


    @ExperimentalComposeUiApi
    @Preview
    @Composable
    fun DragGestureDemo() {
        var boxSize = 300.dp

        var isOneFinger = remember { mutableStateOf(true) }

        var offset by remember { mutableStateOf(Offset.Zero) }
        var ratationAngle by remember { mutableStateOf(0f) }
        var scale by remember { mutableStateOf(1f) }
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(Modifier
                .size(boxSize)
                .rotate(ratationAngle) // 需要注意offset与rotate的调用先后顺序
                .scale(scale = scale)
                .offset {
                    IntOffset(offset.x.roundToInt(), offset.y.roundToInt())
                }
                .background(Color.Green)
                .pointerInput(Unit) {
//                    detectDragGesturesAfterLongPress { change, dragAmount ->
//                        Log.d("detectDragGesturesAfterLongPress--->", "ds")
//                    }

                    detectTransformGestures(
                        panZoomLock = true, // 平移或放大时是否可以旋转
                        onGesture = { centroid: Offset, pan: Offset, zoom: Float, rotation: Float ->
                            //一个手指的时候禁止滑动缩放
                            if (! isOneFinger.value) {
                                offset += pan
                                scale *= zoom
                                ratationAngle += rotation
                            }
                        }
                    )



                    detectDragGestures(
                        onDragStart = { offset ->
                            Log.d("detectDragGestures--->", "onDragStart")
                            // 拖动开始
                        },
                        onDragEnd = {
                            // 拖动结束
                            Log.d("detectDragGestures--->", "onDragEnd")
                        },
                        onDragCancel = {
                            // 拖动取消
                            Log.d("detectDragGestures--->", "onDragCancel")
                        },
                        onDrag = { change: PointerInputChange, dragAmount: Offset ->
                            // 拖动中
                            offset += dragAmount
                            Log.d("detectDragGestures--->", "拖动中")
                        }
                    )
                }

                .pointerInteropFilter {
                    isOneFinger.value = it.pointerCount == 1
                    when (it.action) {
                        MotionEvent.ACTION_DOWN           -> {
                            Log.d("--->", "ACTION_DOWN  ${it.x}  ${it.y}")
                        }
                        MotionEvent.ACTION_MOVE           -> {
                            Log.d("--->", "ACTION_MOVE")
                        }
                        MotionEvent.ACTION_POINTER_DOWN   -> {
                            Log.d("--->", "有多余的手指点击了")
                            Log.d("--->", "${it.actionIndex}")
                            Log.d("--->", "${it.getPointerId(it.actionIndex)}")

                        }
                        MotionEvent.ACTION_POINTER_UP     -> {
                            Log.d("--->", "有多余的手指离开了")
                        }
                        MotionEvent.ACTION_UP             -> {
                            Log.d("--->", "ACTION_UP")
                        }
                        MotionEvent.ACTION_POINTER_2_DOWN -> {
                            Log.d("--->", "ACTION_POINTER_2_DOWN")
                        }
                        MotionEvent.ACTION_POINTER_2_UP   -> {
                            Log.d("--->", "ACTION_POINTER_2_UP")
                        }
                        MotionEvent.ACTION_POINTER_3_DOWN -> {
                            Log.d("--->", "ACTION_POINTER_3_DOWN")
//                            return@pointerInteropFilter false
                        }
                        MotionEvent.ACTION_POINTER_3_UP   -> {
                            Log.d("--->", "ACTION_POINTER_3_UP")
//                            return@pointerInteropFilter false
                        }
                        773                               -> {
                            Log.d("--->", "四个手指 DOWN")
                            return@pointerInteropFilter false
                        }

                        774                               -> {
                            Log.d("--->", "四个手指 UP")
                            return@pointerInteropFilter false
                        }

                        1029                              -> {
                            Log.d("--->", "五个手指 DOWN")
                        }
                        1030                              -> {
                            Log.d("--->", "五个手指 DOWN")
                        }

                        MotionEvent.ACTION_OUTSIDE        -> {
                            Log.d("--->", "ACTION_OUTSIDE ")
                        }
                        MotionEvent.ACTION_CANCEL         -> {
                            Log.d("--->", "ACTION_CANCEL ")
                        }

                        else                              -> {
                            //四手指 773 放下 774离开
                            //五手指 1029放下 1030离开
                            Log.d("--->", "其他")
                            Log.d("--->", "${it.action}")
                        }
                    }

                    true
                }
            )
        }
    }

    @Preview
    @Composable
    fun TransformGestureDemo() {
        var boxSize = 200.dp
        var offset by remember { mutableStateOf(Offset.Zero) }
        var ratationAngle by remember { mutableStateOf(0f) }
        var scale by remember { mutableStateOf(1f) }
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(Modifier
                .size(boxSize)
                .rotate(ratationAngle) // 需要注意offset与rotate的调用先后顺序
                .scale(scale = scale)
                .offset {
                    IntOffset(offset.x.roundToInt(), offset.y.roundToInt())
                }
                .background(Color.Green)
                .pointerInput(Unit) {
                    detectTransformGestures(
                        panZoomLock = true, // 平移或放大时是否可以旋转
                        onGesture = { centroid: Offset, pan: Offset, zoom: Float, rotation: Float ->
                            offset += pan
                            scale *= zoom
                            ratationAngle += rotation
                        }
                    )
                }
            )
        }
    }

}
