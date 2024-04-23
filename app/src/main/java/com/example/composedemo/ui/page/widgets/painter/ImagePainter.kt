package com.example.composedemo.ui.page.widgets.painter

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composedemo.R


@Composable
fun ImagePainter(viewModel: PainterViewModel = hiltViewModel()) {

    val data = viewModel.data.collectAsState().value

    val pathList = remember{
        mutableStateOf(data.pathList.toMutableList())
    }

    Column(Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)) {
            Image(
                painter = painterResource(id = R.mipmap.img_2),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDrag = { change, dragAmount ->
                                val position = change.position
                                if (pathList.value.isEmpty()) return@detectDragGestures

                                val currentPath = pathList.value.last().first
                                if (currentPath.isEmpty) {
                                    currentPath.moveTo(position.x, position.y)
                                } else {
                                    currentPath.lineTo(position.x, position.y)
                                }
                            }, onDragStart = {
                                val path = Path().apply {
                                    moveTo(it.x, it.y)
                                }
                                val paint = Paint()
                                    .apply {
                                        color = Color.White
                                        strokeWidth = viewModel.eraserWidth.dp.toPx()
                                        isAntiAlias = true
                                        style = PaintingStyle.Stroke
                                    }

                                pathList.value.add(path to paint)
                                viewModel.updatePathList(path to paint)
                            },
                            onDragEnd = {
                            }
                        )
                    }
            )

            Canvas( modifier = Modifier.fillMaxSize(),) {
                pathList.value.forEach { data ->
                    drawIntoCanvas { canvas ->
                        canvas.drawPath(data.first, data.second)
                    }
                }
            }
        }


        Slider(
            value = viewModel.eraserWidth,
            onValueChange = { newWidth ->
                viewModel.eraserWidth = newWidth
            },
            valueRange = 1f..100f
        )

        Row (modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly){
            Button(onClick = {
                viewModel.undo()
            }) {
                Text("Undo")
            }

            Button(onClick = {
                viewModel.redo()
            }) {
                Text("Redo")
            }

            Button(onClick = {
                viewModel.isEraseModel = !viewModel.isEraseModel
            }) {
                Text("Switch Mode")
            }
        }
    }
}