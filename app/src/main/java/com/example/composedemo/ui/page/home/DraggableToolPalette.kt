package com.example.composedemo.ui.page.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import kotlin.math.roundToInt

@Composable
private fun DraggableToolPalette(
    modifier: Modifier = Modifier,
    snap: Boolean = true,
    content: @Composable BoxScope.() -> Unit,
) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val configuration = LocalConfiguration.current
    var childSize by remember { mutableStateOf(IntSize.Zero) }

    Box(modifier = modifier
        .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
        .pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                change.consume()
                offsetX += dragAmount.x
                offsetY += dragAmount.y
                val snapDistance = 10.dp.toPx()
                val maxY = if (snap) {
                    childSize.height.toFloat()
                } else {
                    48.dp.toPx()
                }
                val maxX = if (snap) {
                    childSize.width.toFloat()
                } else {
                    48.dp.toPx()
                }

                if (offsetX < 0f || (snap && offsetX < snapDistance)) {
                    offsetX = 0f
                }
                if (offsetY < 0f || (snap && offsetY < snapDistance)) {
                    offsetY = 0f
                }
                val width = configuration.screenWidthDp.dp.toPx() - maxX
                val height = configuration.screenHeightDp.dp.toPx() - maxY
                if (offsetX > width || (snap && offsetX > width - snapDistance)) {
                    offsetX = width
                }

                if (offsetY > height || (snap && offsetY > height - snapDistance)) {
                    offsetY = height
                }
            }
        }
    ) {
        Box(modifier = Modifier
            .onGloballyPositioned { coordinates ->
                childSize = coordinates.size
            }
            .padding(4.dp)
            .shadow(16.dp)
            .border(2.dp, Color(0.9f, 0.9f, 0.9f, 1f), shape = RoundedCornerShape(8.dp))
            .background(Color(0.9f, 0.9f, 0.9f, 0.9f), shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
            .wrapContentWidth()
            .wrapContentHeight()

        ) {
            content()
        }
    }
}

/**
 * webview实现的 不好玩
 */
@Composable
fun DraggableToolPalette(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        Column(modifier = Modifier.fillMaxSize()) {
            DraggableToolPalette(modifier = Modifier) {
                Column() {
                    Text("Tools")
                    Row {
                        OutlinedButton(onClick = {

                        }) {
                            Icon(Icons.Default.Edit, "Edit")
                        }
                        OutlinedButton(onClick = {

                        }) {
                            Icon(Icons.Default.Delete, "Delete")
                        }
                        OutlinedButton(onClick = {

                        }) {
                            Icon(Icons.Default.Call, "Call")
                        }
                    }
                }
            }

            DraggableToolPalette(snap = false) {
                Column() {
                    Text("Tools")

                    OutlinedButton(onClick = {

                    }) {
                        Icon(Icons.Default.Edit, "Edit")
                    }
                    OutlinedButton(onClick = {

                    }) {
                        Icon(Icons.Default.Delete, "Delete")
                    }
                    OutlinedButton(onClick = {

                    }) {
                        Icon(Icons.Default.Call, "Call")
                    }

                }
            }
        }

    }
}

