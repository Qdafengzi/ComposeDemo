package com.example.composedemo.ui.page.anim

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

/**
 * Created by finn on 2022/3/22
 */
@Composable
fun TouchAnimationPage(navCtrl: NavHostController, title: String) {

    val currentPositionColor = Color.White
    val lastPositionColor = Color.LightGray
    var cacheOffset by remember() {
        mutableStateOf(Offset.Zero)
    }

    var lastOffset by remember() {
        mutableStateOf(Offset.Zero)
    }

    val animatedOffset = remember {
        Animatable(Offset(0f, 0f), Offset.VectorConverter)
    }
    val pxValue = with(LocalDensity.current) { 40.dp.toPx() } / 2f

    CommonToolbar(navCtrl, title) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Magenta)
            .pointerInput(Unit) {
                coroutineScope {
                    while (true) {
                        val offset = awaitPointerEventScope {
                            awaitFirstDown().position
                        }
                        lastOffset = cacheOffset
                        cacheOffset = offset
                        launch {
                            animatedOffset.animateTo(
                                offset,
                                animationSpec = spring(stiffness = Spring.StiffnessLow)
                            )
                        }
                    }
                }
            }
            .drawBehind {
                drawCircle(color = currentPositionColor, radius = pxValue, center = cacheOffset)
                drawCircle(color = lastPositionColor, radius = pxValue, center = lastOffset)
            }
        ) {
            Box(
                Modifier
                    .offset {
                        IntOffset(
                            (animatedOffset.value.x - pxValue).roundToInt(),
                            (animatedOffset.value.y - pxValue).roundToInt()
                        )
                    }
                    .size(40.dp)
                    .background(MaterialTheme.colors.primary, CircleShape)
            )
        }
    }
}
