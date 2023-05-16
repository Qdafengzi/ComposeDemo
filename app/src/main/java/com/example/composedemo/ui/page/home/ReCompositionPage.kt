package com.example.composedemo.ui.page.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch

private val lists = persistentListOf("香蕉", "苹果", "芒果", "萝卜", "咖啡")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReCompositionPage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
//        val lists = remember {
//            mutableStateListOf("香蕉", "苹果", "芒果", "萝卜", "咖啡")
//        }
        val state = rememberPagerState()
        val scope = rememberCoroutineScope()
        Column {
            ScrollableTabRow(
                selectedTabIndex = state.currentPage,//展示的页码，和Pager的保持一致
                backgroundColor = Color.Green
            ) {
                lists.forEachIndexed { index, data ->
                    Box(
                        Modifier
                            .height(40.dp)
                            .width(100.dp)
                            .clickable {
                                scope.launch {
                                    state.scrollToPage(index, 0f)//Tab被点击后让Pager中内容动画形式滑动到目标页
                                }
                            }, contentAlignment = Alignment.Center
                    ) {
                        Text(text = data,color = Color.Black)
                    }
                }
            }
            HorizontalPager(
                state = state,//Pager当前所在页数
                modifier = Modifier.height(300.dp),
                pageCount = lists.size,
                ) { pagePosition ->

                val color = (0..255)
                Box(
                    Modifier
                        .fillMaxSize()
                        .background(Color(color.random(), color.random(), color.random())),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = lists[pagePosition])
                }
            }
        }
    }
}