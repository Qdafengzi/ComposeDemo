package com.example.composedemo.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
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
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

class CanvasActivity : AppCompatActivity() {

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DragGestureDemo()
//            TransformGestureDemo()
        }
    }

    @ExperimentalComposeUiApi
    @Preview
    @Composable
    fun DragGestureDemo() {
        var boxSize = 100.dp
        var offset by remember { mutableStateOf(Offset.Zero) }
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(Modifier
                .size(boxSize)
                .offset {
                    IntOffset(offset.x.roundToInt(), offset.y.roundToInt())
                }
                .background(Color.Green)

                .pointerInteropFilter {
                    when(it.action){
                        MotionEvent.ACTION_DOWN -> {
                            Log.d("--->","ACTION_DOWN")
                        }
                        MotionEvent.ACTION_MOVE -> {
                            Log.d("--->","ACTION_MOVE")
                        }
                        MotionEvent.ACTION_POINTER_DOWN->{
                            Log.d("--->","有多余的手指点击了")
                            Log.d("--->","${it.actionIndex}")
                            Log.d("--->","${it.getPointerId(it.actionIndex)}")

                        }
                        MotionEvent.ACTION_POINTER_UP->{
                            Log.d("--->","有多余的手指离开了")
                        }

                        else                    -> {
                            Log.d("--->","其他")
                        }
                    }

                    true
                }
                .pointerInput(Unit) {

                    awaitPointerEventScope {
                        val event = awaitPointerEvent(PointerEventPass.Final)

                        Log.d("--->","${event.changes[0].consumed.downChange}")


                    }
                    detectDragGestures(


                        onDragStart = { offset ->
                            Log.d("--->","onDragStart")
                            // 拖动开始
                        },
                        onDragEnd = {
                            // 拖动结束
                            Log.d("--->","onDragEnd")
                        },
                        onDragCancel = {
                            // 拖动取消
                            Log.d("--->","onDragCancel")
                        },
                        onDrag = { change: PointerInputChange, dragAmount: Offset ->
                            // 拖动中
                            offset += dragAmount
                            Log.d("--->","拖动中")
                        }
                    )
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
