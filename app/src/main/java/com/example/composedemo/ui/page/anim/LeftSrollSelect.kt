package com.example.composedemo.ui.page.anim

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import com.example.composedemo.utils.XLogger
import kotlin.math.roundToInt


@Composable
fun LeftScrollSelect(navCtrl: NavHostController, title: String) {
    val list = remember {
        mutableStateListOf<String>()
    }

    repeat(20) {
        list.add("Item $it")
    }

    CommonToolbar(navCtrl, title) {
        LazyColumn(modifier = Modifier.fillMaxWidth(), content = {
            itemsIndexed(items = list, key = { index, _ ->
                index
            }, itemContent = { _, item ->

                var selectedFlag by remember { mutableStateOf(false) }
                var animateEnabled by remember { mutableStateOf(false) }

                var targetValue by remember {
                    mutableFloatStateOf(0f)
                }

                val offsetX: Float by animateFloatAsState(
                    targetValue = targetValue,
                    finishedListener = {
                        animateEnabled = false
                        targetValue = 0f
                    },
                    label = ""
                )

                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedFlag = false
                            animateEnabled = false
                            targetValue = 0f
                        }
                        .offset { IntOffset(offsetX.roundToInt(), 0) }
                        .draggable(
                            orientation = Orientation.Horizontal,
                            state = rememberDraggableState { delta ->
                                XLogger.d("============>$delta")
                                if (delta > 20f) {
                                    targetValue = 50f
                                    animateEnabled = true
                                    selectedFlag = true
                                } else if (delta < -20f) {
                                    targetValue = -50f
                                    animateEnabled = true
                                    selectedFlag = true
                                }
                            }
                        )
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                        .background(color = if (selectedFlag) Color.Blue.copy(alpha = 0.8f) else Color.LightGray)
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    color = Color.Black,

                    )
            })
        })
    }
}












