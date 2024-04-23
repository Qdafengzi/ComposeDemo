package com.example.composedemo.ui.page.widgets.painter

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asComposePaint
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composedemo.R

@Composable
fun EraserCanvas(
    modifier: Modifier = Modifier,
    eraserWidth: Dp = 20.dp,
    eraserCallback: EraserCallback?,
    isEraserMode: Boolean = true
) {


    val paint = remember { android.graphics.Paint().asComposePaint() }
    val canvasBitmap = remember { mutableStateOf<Bitmap?>(null) }
    val pathList = remember { mutableStateListOf<Pair<Path, Paint>>() }
//    val drawScope = remember { androidx.compose.ui.graphics.drawscope.DrawScope() }

    // 加载和缩放位图资源
    val imageBitmap: ImageBitmap = ImageBitmap.imageResource(id = R.mipmap.img_2)
    val density = LocalDensity.current
    val eraserWidthPx = with(density) { eraserWidth.toPx() }

    Canvas(
        modifier = modifier
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        val path = Path()
                        path.moveTo(offset.x, offset.y)
                        paint.apply {
                            color = if (isEraserMode) Color.Transparent else
                                Color(
                                    canvasBitmap.value!!.getPixel(
                                        offset.x.toInt(),
                                        offset.y.toInt()
                                    )
                                )
                            this.blendMode = if (isEraserMode) BlendMode.Clear else BlendMode.Color
//                            xfermode = if (isEraserMode) PorterDuffXfermode(PorterDuff.Mode.Clear) else null
//                            isStroke = true
                            this.style = PaintingStyle.Stroke
                            strokeWidth = eraserWidthPx
                            isAntiAlias = true
                        }
                        pathList.add(path to paint)
                    },
                    onDrag = { change, _ ->
                        val currentPath = pathList.last().first
                        currentPath.lineTo(change.position.x, change.position.y)
                        eraserCallback?.onMove(change.position.x, change.position.y)
                    }
                )
            }
    ) {
        if (canvasBitmap.value == null
            || canvasBitmap.value!!.width != size.width.toInt()
            || canvasBitmap.value!!.height != size.height.toInt()
            ) {
            canvasBitmap.value = Bitmap.createBitmap(
                size.width.toInt(),
                size.height.toInt(),
                Bitmap.Config.ARGB_8888
            ).apply {
                val canvas = Canvas(this)
                val srcRect = Rect(0, 0, imageBitmap.width, imageBitmap.height)
                val dstRect = Rect(0, 0, size.width.toInt(), size.height.toInt())
                canvas.drawBitmap(imageBitmap.asAndroidBitmap(), srcRect, dstRect, null)
            }
        }

        drawIntoCanvas { canvas ->
            canvasBitmap.value?.let {
                canvas.drawImage(it.asImageBitmap(), Offset.Zero, Paint())
            }

            pathList.forEach { (path, frameworkPaint) ->
                canvas.drawPath(path, frameworkPaint)
            }
        }
    }
}

@Composable
fun PainterImageCompose(viewModel: PainterViewModel = hiltViewModel()) {
    val context = LocalContext.current
    var touchPosition by remember { mutableStateOf(Offset.Zero) }
//    val eraserView = remember { EraserView(context, eraserWidth = viewModel.eraserWidth) }

    val isEraseModel = remember {
        mutableStateOf(false)
    }

//    eraserView.callback = object : EraserCallback {
//        override fun onMove(x: Float, y: Float) {
//            touchPosition = Offset(x, y)
//        }
//    }

    Column {
        Slider(
            value = viewModel.eraserWidth,
            onValueChange = { newWidth ->
                viewModel.eraserWidth = newWidth
            },
            valueRange = 1f..100f
        )

        Button(onClick = {
//            eraserView.undo()
        }) {
            Text("Undo")
        }

        Button(onClick = {
//            eraserView.redo()
        }) {
            Text("Redo")
        }

        Button(onClick = {
//            eraserView.isEraserMode = !eraserView.isEraserMode
            isEraseModel.value = !isEraseModel.value
        }) {
            Text("Switch Mode")
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
        ) {
//            AndroidView(
//                modifier = Modifier
//                    .fillMaxSize(),
//                factory = { eraserView },
//                update = { view -> view.eraserWidth = viewModel.eraserWidth }
//            )


            EraserCanvas(
                modifier = Modifier
                    .fillMaxSize(),
                eraserWidth = viewModel.eraserWidth.dp,
                eraserCallback = object : EraserCallback {
                    override fun onMove(x: Float, y: Float) {
                        touchPosition = Offset(x, y)
                    }

                },
                isEraserMode = isEraseModel.value
            )


            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    color = Color.Black,
                    radius = viewModel.eraserWidth / 2,
                    center = touchPosition,
                    style = Stroke(width = 1.dp.toPx())
                )
            }
        }
    }
}