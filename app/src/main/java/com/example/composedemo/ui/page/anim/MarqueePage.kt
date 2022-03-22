package com.example.composedemo.ui.page.anim

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composedemo.R
import com.example.composedemo.ui.widget.CommonToolbar

/**
 * 跑马灯
 * Created by finn on 2022/3/21
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MarqueePage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.mipmap.coil_icon1),
                    contentDescription = "",
                    contentScale = ContentScale.FillHeight
                )

                val list = mutableListOf<String>()
                repeat(20) {
                    list.add("156****78${it} ${it - 2}分钟前购买了...")
                }

                //跑马灯


                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    //ItemMarquess(list[0])
                    var visible by remember { mutableStateOf(true) }
                    val density = LocalDensity.current
                    AnimatedVisibility(
                        visible = visible,
                        enter = slideInVertically {
                            // Slide in from 40 dp from the top.
                            with(density) { -40.dp.roundToPx() }
                        } + expandVertically(
                            // Expand from the top.
                            expandFrom = Alignment.Top
                        ) + fadeIn(
                            // Fade in with the initial alpha of 0.3f.
                            initialAlpha = 0.3f
                        ),
                        exit = slideOutVertically() + shrinkVertically() + fadeOut()
                    ) {
                        Text(text = "Hello",color = Color.White, modifier = Modifier.fillMaxWidth().height(200.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun ItemMarquess(text: String) {
    Box(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .background(color = Color(0x80FFFFFF), shape = RoundedCornerShape(2.dp))
            .padding(top = 20.dp, bottom = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, fontSize = 14.sp, fontWeight = FontWeight.Normal, color = Color.White)
    }
}



