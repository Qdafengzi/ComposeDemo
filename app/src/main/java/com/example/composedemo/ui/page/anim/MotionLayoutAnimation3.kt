package com.example.composedemo.ui.page.anim

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar

/**
 * Created by finn on 2022/9/13
 */

@Composable
fun MotionLayoutAnimation3(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White), contentAlignment = Alignment.Center) {
            MotionLayout3Example()
        }
    }
}

@OptIn(ExperimentalMotionApi::class)
@Composable
private fun MotionLayout3Example() {
    var animateToEnd by remember { mutableStateOf(false) }

    val progress by animateFloatAsState(
        targetValue = if (animateToEnd) 1f else 0f,
        animationSpec = tween(1000)
    )
    Column {
        MotionLayout(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White),
            motionScene = MotionScene(
                """{
                              ConstraintSets: {
                                start: {
                                  backgroundSwipe: {
                                    start: ['parent', 'start', 2],
                                    end: ['parent','end',2],
                                    top: ['parent','top',2],
                                    bottom: ['parent','bottom',2]
                                  },
                                  backgroundButtonSwipe: {
                                    width: "spread",
                                    height: "spread",
                                    start: ['backgroundSwipe', 'start', 0],
                                    end: ['buttonSwipe','end',0],
                                    top: ['backgroundSwipe','top',0],
                                    bottom: ['backgroundSwipe','bottom',0]
                                  },
                                  swipeUp: {
                                    start: ['buttonSwipe', 'start', 0],
                                    end: ['buttonSwipe','end',0],
                                    top: ['buttonSwipe','top',0],
                                    bottom: ['buttonSwipe','bottom',0]
                                  },
                                  buttonSwipe: {
                                    start: ['backgroundSwipe', 'start', 0],
                                    top: ['backgroundSwipe','top',0],
                                    bottom: ['backgroundSwipe','bottom',0],
                                    custom: {
                                      color: "#004DBC"//起始颜色
                                    }
                                  }
                                },
                                end: {
                                  Extends: 'start',
                                  buttonSwipe: {
                                    clear: ['constraints'],//清除
                                    end: ['backgroundSwipe','end',0],
                                    top: ['backgroundSwipe','top',2],
                                    bottom: ['backgroundSwipe','bottom',0],
                                    custom: {
                                      color: "#FF0000"//结束的颜色
                                    }
                                  }
                                }
                                }
                              }
                              """
            ),
            progress = progress
        ) {
            Box(
                modifier = Modifier
                    .layoutId("backgroundSwipe")
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(shape = RoundedCornerShape(40.dp))
                    .background(Color.LightGray)
                    .border(2.dp, Color.DarkGray, shape = RoundedCornerShape(40.dp))

            )

            Box(
                modifier = Modifier
                    .layoutId("backgroundButtonSwipe")
                    .height(80.dp)
                    .clip(shape = RoundedCornerShape(40.dp))
                    .background(Color(0xFF4462D7.toInt()))
                    .border(2.dp, Color.DarkGray, shape = RoundedCornerShape(40.dp))
            )

            Box(
                modifier = Modifier
                    .layoutId("buttonSwipe")
                    .height(78.dp)
                    .width(78.dp)
                    .clip(shape = CircleShape)
                    .background(motionProperties("buttonSwipe").value.color("color"))//从motion属性获取颜色
                    .clickable(onClick = { animateToEnd = !animateToEnd })

            )
            Icon(
                Icons.Sharp.KeyboardArrowRight,
                contentDescription = "null",
                tint = Color.White,
                modifier = Modifier
                    .layoutId("swipeUp")
                    .width(40.dp)
                    .height(40.dp)
            )
        }
    }
}
