package com.example.composedemo.ui.page.anim

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import com.example.composedemo.utils.XLogger

@Composable
fun CanvasDrag(navCtrl: NavHostController, title: String) {
    XLogger.d("=========CanvasDrag")
    val offset = remember { mutableStateOf(0f) }
    val maxScrollOffset = remember { mutableStateOf(0f) }
    val globalXScale = 1f
    val dragOffset = remember { mutableStateOf(0f) }
    val isDragging = remember { mutableStateOf(false) }
    val xZoom = remember { mutableStateOf(globalXScale) }


    CommonToolbar(navCtrl, title) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Cyan)
        ) {
            Canvas(modifier = Modifier
                .padding(8.dp)
                .aspectRatio(3 / 2f)//宽高比 3：2
                .fillMaxSize()
                .align(Alignment.Center)
                .background(Color.DarkGray)
                .scrollable(
                    state = rememberScrollableState { delta ->
                        offset.value -= delta
                        if (offset.value < 0f) offset.value = 0f
                        if (offset.value > maxScrollOffset.value) {
                            offset.value = maxScrollOffset.value
                        }
                        delta
                    }, Orientation.Horizontal, enabled = true
                )
                .pointerInput(Unit, Unit) {
                    detectDragZoomGesture(
                        isZoomAllowed = true,
                        isDragAllowed = true,
                        detectDragTimeOut = 300,
                        onDragStart = {

                            dragOffset.value = it.x
                            isDragging.value = true
                        }, onDragEnd = {
                            isDragging.value = false
                        }, onZoom = { zoom ->
                            xZoom.value *= zoom
                        }
                    ) { change, _ ->
                        dragOffset.value = change.position.x
                    }
                }) {
                XLogger.d("=========>size:" + size.width)
                //9个
                val space = 4.dp.toPx()
                val width = (size.width - 3 * space) / 4
                repeat(10) {
                    drawRect(
                        color = Color.Blue,
                        topLeft = Offset(it * width + it * space, 0f),
                        size = Size(width = width, height = width)
                    )
                }
            }
//                .horizontalScroll(state = rememberScrollState())

        }
    }
}