package com.example.composedemo.ui.page.anim

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.layoutId
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar

/**
 * Created by finn on 2022/9/13
 */

@Composable
fun MotionLayoutAnimationTwo(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .background(color = Color.Magenta), contentAlignment = Alignment.Center) {
            MotionLayoutButtonTwo()
        }
    }
}

@OptIn(ExperimentalUnitApi::class, ExperimentalMotionApi::class)
@Composable
fun MotionLayoutButtonTwo() {
    var animateButton by remember { mutableStateOf(false) }
    val buttonAnimationProgress by animateFloatAsState(
        targetValue = if (animateButton) 1f else 0f,
        animationSpec = tween(1000)
    )

    MotionLayout(
        ConstraintSet(
            """ {
                button1: {
                  width: 150,
                  height: 120,
                  start: ['parent', 'start', 16],
                  top: ['parent', 'top', 0],

                },
                button2: {
                  width: 150,
                  height: 120,
                  start: ['parent', 'start', 16],
                  end: ['parent', 'end', 16],
                  bottom: ['parent', 'bottom', 16],
                }
            } """
        ),

        ConstraintSet(
            """ {
                button1: {
                  width: 100,
                  height: 80,
                  start: ['parent', 'start', 30],
                  end: ['button2', 'start', 10]
                },
                button2: {
                  width: 100,
                  height: 80,
                  start: ['button1', 'end', 10],
                  end: ['parent', 'end', 30]
                }
            } """
        ),
        progress = buttonAnimationProgress,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Button(
            onClick = {
                animateButton = !animateButton
            },
            modifier = Modifier.layoutId("button1")
        ) {
            Column(
                modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = com.example.composedemo.R.drawable.ic_book),
                    contentDescription = "Book",
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Book",
                    color = Color.White,
                    fontSize = TextUnit(value = 18F, type = TextUnitType.Sp)
                )
            }
        }

        Button(
            onClick = {
                animateButton = !animateButton
            },
            modifier = Modifier.layoutId("button2")
        ) {
            Column(
                modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = com.example.composedemo.R.drawable.ic_home),
                    contentDescription = "Home",
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Home",
                    color = Color.White,
                    fontSize = TextUnit(value = 18F, type = TextUnitType.Sp)
                )
            }
        }
    }
}