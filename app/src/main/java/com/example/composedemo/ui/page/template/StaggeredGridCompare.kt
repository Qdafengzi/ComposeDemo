package com.example.composedemo.ui.page.template

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.composedemo.flinger.StockFlingBehaviours
import com.example.composedemo.ui.widget.CommonToolbar
import com.example.composedemo.ui.widget.LazyStaggeredVerticalGrid
import com.example.composedemo.ui.widget.StaggeredVerticalGrid
import com.example.composedemo.utils.XLogger
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

/**
 * Created by finn on 2022/4/28
 */
private val rangeForRandom = (0..100000)


data class StaggeredItem(
    val name: String,
    val height: Int,
    val color: Color,
    val picture: String,
)


fun getStaggeredList(): MutableList<StaggeredItem> {
    val list = mutableListOf<StaggeredItem>()
    val heightList = listOf(300, 320, 350, 280)
    repeat(500) {
        val height = heightList.random()
        val picture = "https://picsum.photos/seed/${rangeForRandom.random()}/500/$height"
        list.add(
            StaggeredItem(
                name = "name $it",
                height = height,
                color = Color(
                    red = (0..255).random(),
                    blue = (0..255).random(),
                    green = (0..255).random()
                ),
                picture = picture
            )
        )
    }
    return list
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun StaggeredGridCompare(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        val scope = rememberCoroutineScope()
        val pages = listOf("One", "Two", "Three")
        val pagerState = rememberPagerState()

        TabRow(
            // Our selected tab is our current page
            selectedTabIndex = pagerState.currentPage,
            // Override the indicator, using the provided pagerTabIndicatorOffset modifier
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {
            // Add tabs for all of our pages
            pages.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.scrollToPage(index)
                        }
                    },
                )
            }
        }

        HorizontalPager(
            count = pages.size,
            state = pagerState,
        ) { page ->
            when (page) {
                0 -> {
                    ListOne()
                }
                1 -> {
                    ListTwo()
                }
                2 -> {
                    ListThree()
                }
            }
        }
    }
}

@Composable
fun ListOne() {
    val list = getStaggeredList()
    Column(
        modifier = Modifier.verticalScroll(
            rememberScrollState(),
            flingBehavior = StockFlingBehaviours.smoothScroll()
        )
    ) {
        StaggeredVerticalGrid(
            maxColumnWidth = 220.dp,
            modifier = Modifier.padding(4.dp)
        ) {
            list.forEachIndexed { _, staggeredItem ->
                StaggeredItem(staggeredItem)
            }
        }
    }
}


/**
 * 拉垮！！！！！！
 */
@Composable
fun ListTwo() {
    val list = getStaggeredList()
//    LazyStaggeredGrid(modifier = Modifier.fillMaxWidth(), cells = StaggeredCells.Fixed(2)) {
//        items(count = list.size) {
//            val item = list[it]
//            StaggeredItem(item)
//        }
//    }
    com.example.composedemo.ui.widget.grid.LazyStaggeredGrid(columnCount = 2) {
        list.forEachIndexed { _, staggeredItem ->
            item {
                StaggeredItem(staggeredItem)
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListThree() {
    val list = getStaggeredList()
    LazyStaggeredVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        cells = GridCells.Fixed(2),
        content = {
            items(count = list.size) {
                val item = list[it]
                StaggeredItem(item)
            }
        },
        headContent = {

        })
}


@Composable
fun StaggeredItem(staggeredItem: StaggeredItem) {
    Box(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .background(color = staggeredItem.color, shape = RoundedCornerShape(9.dp))
            .fillMaxWidth()
            .height(staggeredItem.height.dp),
        contentAlignment = Alignment.Center
    ) {
        XLogger.d("---->${staggeredItem.picture}")
        Image(
            painter = rememberImagePainter(data = staggeredItem.picture),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(9.dp))
        )
        Text(
            text = staggeredItem.name,
            color = Color.White,
            fontSize = 25.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

