package com.example.composedemo.ui.page.home.swipe

import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun ScrollMotionSwipeDemo() {
    val density = LocalDensity.current

    val defaultActionSize = 100.dp
    val itemHeight = 68.dp
    val actionSizePx = with(density) { defaultActionSize.toPx() }
    val endActionSizePx = with(density) { (defaultActionSize * 2).toPx() }


    val startActionSizePx = with(density) { defaultActionSize.toPx() }
    val decayAnimationSpec = remember { exponentialDecay<Float>() }


    val state = remember {
        AnchoredDraggableState(
            initialValue = DragAnchors.Center,
            anchors = DraggableAnchors {
                DragAnchors.Start at -startActionSizePx
                DragAnchors.Center at 0f
                DragAnchors.End at endActionSizePx
            },
            positionalThreshold = { distance: Float -> distance * 0.5f },
            velocityThreshold = { with(density) { 100.dp.toPx() } },
//            velocityThreshold = 1f,
            snapAnimationSpec = tween(),
            decayAnimationSpec = decayAnimationSpec

//            snapAnimationSpec = tween(),
//            decayAnimationSpec = tween(),
//            confirmValueChange = true,

//            animationSpec = tween(),
        )
    }

    DraggableItem(state = state,
        startAction = {
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .align(Alignment.CenterStart),
            ) {
                SaveAction(
                    Modifier
                        .width(defaultActionSize)
                        .height(100.dp)
                        .offset {
                            IntOffset(
                                ((-state
                                    .requireOffset() - actionSizePx))
                                    .roundToInt(), 0
                            )
                        }
                )
            }
        },
        endAction = {
            Row(
                modifier = Modifier
                    .height(100.dp)
                    .align(Alignment.CenterEnd)
                    .offset {
                        IntOffset(
                            ((-state
                                .requireOffset()) + endActionSizePx)
                                .roundToInt(), 0
                        )
                    }
            )
            {
                EditAction(
                    Modifier
                        .width(defaultActionSize)
                        .fillMaxHeight()

                )
                DeleteAction(
                    Modifier
                        .width(defaultActionSize)
                        .fillMaxHeight()
                )
            }
        }, content = {
            HelloWorldCard()
        })
}