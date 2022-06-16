package com.example.composedemo.ui.page.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.layoutId
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar


@Composable
fun MotionLayoutPage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        MotionLayoutOne()
    }
}


@OptIn(ExperimentalMotionApi::class)
@Composable
fun MotionLayoutOne() {
    var animateToEnd by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (animateToEnd) 1f else 0f,
        animationSpec = tween(1000)
    )

    Column(Modifier.background(Color.White)) {
        MotionLayout(
            ConstraintSet(
                """ {
                    background: { 
                width: "spread",
                height: 60,
                start: ['parent', 'start', 16],
                bottom: ['parent', 'bottom', 16],
                end: ['parent', 'end', 16]
                },
                v1: { 
                width: 100,
                height: 60,
                start: ['parent', 'start', 16],
                bottom: ['parent', 'bottom', 16]
                },
                title: { 
                width: "spread",
                start: ['v1', 'end', 8],
                top: ['v1', 'top', 8],
                end: ['parent', 'end', 8],
                custom: {
                  textSize: 14
                }
                },
                description: { 
                start: ['v1', 'end', 8],
                top: ['title', 'bottom', 0],
                custom: {
                  textSize: 12
                }
                },
                list: { 
                width: "spread",
                height: 0,
                start: ['parent', 'start', 8],
                end: ['parent', 'end', 8],
                top: ['parent', 'bottom', 0]
                },

                close: { 
                end: ['parent', 'end', 24],
                top: ['v1', 'top', 0],
                bottom: ['v1', 'bottom', 0]
                }
            } """
            ),
            ConstraintSet(
                """ {
                background: { 
                width: "spread",
                height: 100,
                start: ['parent', 'start', 0],
                end: ['parent', 'end', 0],
                top: ['parent', 'top', 0]
                },
                v1: { 
                width: "spread",
                height: 100,
                start: ['parent', 'start', 0],
                end: ['parent', 'end', 0],
                top: ['parent', 'top', 0]
                },
                title: { 
                width: "spread",
                height: 28,
                start: ['parent', 'start', 16],
                top: ['background', 'tap', 16],
                end: ['parent', 'end', 16],
                custom: {
                  textSize: 20
                }
                },
                description: { 
                width: "spread",
                start: ['parent', 'start', 16],
                top: ['v1', 'bottom', 8],
                end: ['parent', 'end', 16],
                custom: {
                  textSize: 14
                }
                },
                list: { 
                width: "spread",
                height: 550,
                start: ['parent', 'start', 16],
                end: ['parent', 'end', 16],
                top: ['description', 'bottom', 16]
                },
                close: { 
                start: ['parent', 'end', 8],
                top: ['v1', 'top', 0],
                bottom: ['v1', 'bottom', 0]
                }
            } """
            ),
            progress = progress,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .layoutId("background", "box")
                    .background(Color.Cyan)
                    .clickable(onClick = { animateToEnd = !animateToEnd })
            )
            Button(
                onClick = { animateToEnd = !animateToEnd },
                modifier = Modifier
                    .layoutId("v1", "box")
                    .background(Color.Blue),
                shape = RoundedCornerShape(0f)
            ) {}

            Text(
                text = "MotionLayout",
                modifier = Modifier.layoutId(layoutId = "title", tag = "title"),
                color = Color.Black,
                fontSize = motionProperties("title").value.fontSize("textSize")
            )
            Text(
                text = "jetpack Compose ",
                modifier = Modifier.layoutId(layoutId = "description", tag = "description"),
                color = Color.Black,
                fontSize = motionProperties("description").value.fontSize("textSize")
            )
            Box(
                modifier = Modifier
                    .layoutId("list", "box")
                    .background(Color.Gray)
            )

            Icon(
                Icons.Filled.Close,
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.layoutId(layoutId = "close", tag = "close"),
            )
        }
    }
}
