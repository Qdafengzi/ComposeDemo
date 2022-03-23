package com.example.composedemo.ui.page.template

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.composedemo.R
import com.example.composedemo.ui.widget.CommonToolbar

/**
 * Created by finn on 2022/3/7
 */
@Composable
fun ImageZoom(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
//        val scale = remember { mutableStateOf(1f) }
//        val rotationState = remember { mutableStateOf(1f) }
//        var offset by remember { mutableStateOf(Offset.Zero) }

//        Box(
//            modifier = Modifier
//                .clip(RectangleShape) // Clip the box content
//                .fillMaxSize() // Give the size you want...
//                .background(Color.White)
//                .pointerInput(Unit) {
//                    detectTransformGestures { centroid, pan, zoom, rotation ->
//                        offset += pan * 1.5f
//                        scale.value *= zoom
//                        rotationState.value += rotation
//                    }
//                }
//        ) {
//            Image(
//                modifier = Modifier
//                    .align(Alignment.Center) // keep the image centralized into the Box
//                    .graphicsLayer(
//                        // adding some zoom limits (min 50%, max 200%)
//                        scaleX = maxOf(.5f, minOf(3f, scale.value)),
//                        scaleY = maxOf(.5f, minOf(3f, scale.value)),
//                        rotationZ = rotationState.value,
//                        translationX = if (scale.value > 1f) offset.x else 0f,
//                        translationY = if (scale.value > 1f) offset.y else 0f
//                    )
//                    .pointerInput(Unit) {
//                        detectTapGestures(
//                            //双击 还原
//                            onDoubleTap = {
//                                scale.value = 1f
//                                rotationState.value = 1f
//                                offset = Offset.Zero
//                            }
//                        )
//                    },
//                contentDescription = null,
//                painter = painterResource(R.mipmap.coil_icon1)
//            )
//        }

        ZoomableImage(
            painter = painterResource(id = R.mipmap.coil_icon1),
        )
    }
}


@Composable
fun ZoomableImage(
    painter: Painter,
    maxScale: Float = .30f,
    minScale: Float = 3f,
    contentScale: ContentScale = ContentScale.Fit,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    isRotation: Boolean = false,
    isZoomable: Boolean = true
) {
    val scale = remember { mutableStateOf(1f) }
    val rotationState = remember { mutableStateOf(1f) }
//    val offsetX = remember { mutableStateOf(1f) }
//    val offsetY = remember { mutableStateOf(1f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RectangleShape)
            .background(Color.Transparent)
            .pointerInput(Unit) {
                if (isZoomable) {
                    forEachGesture {
                        awaitPointerEventScope {
                            awaitFirstDown()
                            do {
                                val event = awaitPointerEvent()
                                scale.value *= event.calculateZoom()
                                if (scale.value > 1) {
                                    val offset = event.calculatePan()
//                                    offsetX.value += offset.x
//                                    offsetY.value += offset.y
                                    rotationState.value += event.calculateRotation()
                                } else {
                                    scale.value = 1f
//                                    offsetX.value = 1f
//                                    offsetY.value = 1f
                                }
                            } while (event.changes.any { it.pressed })
                        }
                    }
                }
            },
        contentAlignment = Alignment.Center

    ) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = contentScale,
            modifier = modifier
                .align(Alignment.Center)
                .graphicsLayer {
                    if (isZoomable) {
                        scaleX = maxOf(maxScale, minOf(minScale, scale.value))
                        scaleY = maxOf(maxScale, minOf(minScale, scale.value))
                        if (isRotation) {
                            rotationZ = rotationState.value
                        }
//                        translationX = offsetX.value
//                        translationY = offsetY.value
                    }
                }
        )
    }
}