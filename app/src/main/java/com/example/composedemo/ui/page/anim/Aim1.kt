package com.example.composedemo.ui.page.anim

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar

/**
 * 跑马灯
 * Created by finn on 2022/3/21
 */
@OptIn(ExperimentalAnimationApi::class, com.google.accompanist.pager.ExperimentalPagerApi::class)
@Composable
fun Aim1Page(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {

        var visible by remember { mutableStateOf(true) }

        Column(Modifier.padding(16.dp)) {
            Text("AnimateVisibilityDemo")
            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { visible = !visible }
            ) {
                Text(text = if (visible) "Hide" else "Show")
            }

            Spacer(Modifier.height(16.dp))

            AnimatedVisibility(visible) {
                Box(
                    Modifier
                        .size(128.dp)
                        .background(Color.Blue)
                )
            }
        }

//        var blue by remember { mutableStateOf(true) }
//        val color by animateColorAsState(
//            if (blue) Color.Blue else Color.Red,
//            animationSpec = spring(stiffness = Spring.StiffnessVeryLow),
//            finishedListener = {
//                blue = !blue
//            }
//        )
//
//        Column(Modifier.padding(16.dp)) {
//            Text("AnimateAsStateDemo")
//            Spacer(Modifier.height(16.dp))
//
//            Button(
//                onClick = { blue = !blue }
//            ) {
//                Text("Change Color")
//            }
//            Spacer(Modifier.height(16.dp))
//            Box(
//                Modifier
//                    .size(128.dp)
//                    .background(color)
//            )
//        }


//        val scope = rememberCoroutineScope()
//        val tabs = remember {
//            listOf("Home", "Mine")
//        }
//
////        val backgroundColor = if (tabPage == TabPage.Home) Purple100 else Green300
//
//        val pagerState = rememberPagerState()
//        val backgroundColor by animateColorAsState(if (pagerState.currentPage == 0) Color.Blue else Color.Yellow)
//        TabRow(selectedTabIndex = pagerState.currentPage) {
//            tabs.forEachIndexed { index, s ->
//                Tab(selected = pagerState.currentPage == index, onClick = {
////                    scope.launch {
////                        pagerState.scrollToPage(index)
////                    }
//                }, selectedContentColor = backgroundColor) {
//                    Text(
//                        modifier = Modifier.fillMaxWidth(),
//                        text = s,
//                        color = Color.Black,
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Medium
//                    )
//                }
//            }
//        }
    }
}




