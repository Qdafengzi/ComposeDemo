package com.example.composedemo.ui.page.template

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.R
import com.example.composedemo.ui.widget.CommonToolbar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

/**
 * Created by finn on 2022/4/20
 */


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerPage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        Spacer(modifier = Modifier.height(20.dp))
        PagerOne()
        Spacer(modifier = Modifier.height(20.dp))
        PagerOne(auto = true, autoTime = 2500)
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerOne(auto: Boolean = false, autoTime: Long = 3000L) {
    val list = mutableListOf<String>()
    repeat(10) {
        list.add("Item $it")
    }
    val startIndex = Int.MAX_VALUE / 2
    val pagerState = rememberPagerState(initialPage = startIndex)
    val pageCountIndex = remember {
        derivedStateOf {
            (pagerState.currentPage - startIndex).floorMod(list.size)
        }
    }

    var currentTime by remember {
        mutableStateOf(10L)
    }

    if (auto) {
        LaunchedEffect(key1 = currentTime) {
            delay(autoTime)
            if (pagerState.currentPage == Int.MAX_VALUE - 1) {
                pagerState.animateScrollToPage(0)
            } else {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
            currentTime = System.currentTimeMillis()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16 / 9f)
    ) {
        HorizontalPager(
            count = Int.MAX_VALUE,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 0.dp),
            itemSpacing = 0.dp,
            modifier = Modifier
                .fillMaxSize()
        ) { index ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .background(Color.Blue)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.coil_icon3),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = "index:$index page index:${pageCountIndex.value}",
                    modifier = Modifier
                        .padding(16.dp)
                        .background(MaterialTheme.colors.surface, RoundedCornerShape(4.dp))
                        .sizeIn(minWidth = 40.dp, minHeight = 40.dp)
                        .padding(8.dp)
                        .wrapContentSize(Alignment.Center)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(list.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(start = 2.dp, end = 2.dp)
                        .width(if (index == pageCountIndex.value) 12.dp else 4.dp)
                        .height(4.dp)
                        .clip(if (index == pageCountIndex.value) RoundedCornerShape(2.dp) else CircleShape)
                        .background(
                            color = if (index == pageCountIndex.value) Color.Red else Color.DarkGray,
                            shape = if (index == pageCountIndex.value) RoundedCornerShape(2.dp) else CircleShape
                        )
                )
            }
        }
    }
}

private fun Int.floorMod(other: Int): Int = when (other) {
    0 -> this
    else -> this - floorDiv(other) * other
}