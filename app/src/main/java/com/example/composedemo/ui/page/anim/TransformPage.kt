package com.example.composedemo.ui.page.anim

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import kotlin.math.roundToInt

/**
 * Created by finn on 2022/7/1
 */


@Composable
fun TransformPage(navCtrl: NavHostController, title: String) {
    var cursorOffset by remember() {
        mutableStateOf(Offset.Zero)
    }

    var offset by remember { mutableStateOf(Offset.Zero) }

    val state = rememberTransformableState { _, offsetChange, rotationChange ->
        offset += offsetChange
    }


    CommonToolbar(navCtrl, title) {
        Column(modifier = Modifier.fillMaxSize()) {
//            Box(modifier = Modifier
//                .fillMaxWidth()
//                .height(60.dp)
//                .background(color = Color.Magenta)) {
//
//                Box(modifier = Modifier
//                    .graphicsLayer(translationX = offset.x)
//                    .transformable(state = state,lockRotationOnZoomPan = true, enabled = true)
//                    .background(color = Color.Green)
//                    .size(60.dp)
//                )
//            }

            Drag2DGestures()
        }




    }
}

@Composable
fun Drag2DGestures() {
    // Creating a Column Layout
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

        // Horizontally Draggable Modifier
        var offsetX by remember { mutableStateOf(0f) }
        Text(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        offsetX += delta
                    }
                ),
            text = "I move Horizontally!", fontSize = 20.sp
        )

        // Adding a Space of 100dp height
        Spacer(modifier = Modifier.height(100.dp))

        // Vertically Draggable Modifier
//        var offsetY by remember { mutableStateOf(0f) }
//        Text(
//            modifier = Modifier
//                .offset { IntOffset(0, offsetY.roundToInt()) }
//                .draggable(
//                    orientation = Orientation.Vertical,
//                    state = rememberDraggableState { delta ->
//                        offsetY += delta
//                    }
//                ),
//            text = "I move Vertically!", fontSize = 20.sp
//        )
    }

}