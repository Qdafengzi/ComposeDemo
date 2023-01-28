package com.example.composedemo.ui.page.template

import android.view.LayoutInflater
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavHostController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.composedemo.R
import com.example.composedemo.adapter.YoutubeListAdapter
import com.example.composedemo.data.VideoEntity
import com.example.composedemo.databinding.ActivityYoutubeListRvBinding
import com.example.composedemo.ui.widget.CommonToolbar
import com.example.composedemo.utils.XLogger
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun YoutubeRvPage(navCtrl: NavHostController, title: String) {
    XLogger.d("-------------------->YoutubeRvPage")
    CommonToolbar(navCtrl, title) {
        val coroutineScope = rememberCoroutineScope()
        val pagerState = rememberPagerState()
        var tabs = remember {
            mutableStateListOf("关注", "推荐", "世界杯", "热点", "深圳", "视频", "小说", "数码", "娱乐", "美食")
        }

        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            tabs.add(0,"新增")
            //数据更新
        }) {
            Text(text = "增加频道")
        }

        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()){
            HorizontalPager(
                count = tabs.size,
                state = pagerState,
                userScrollEnabled = false,
                modifier = Modifier
                    .fillMaxSize().padding(top = 40.dp),
            ) { pageIndex ->
                NewsPage(pageIndex)
            }

            ScrollableTabRow(
                modifier = Modifier.fillMaxWidth(),
                selectedTabIndex = pagerState.currentPage,
                contentColor = Color.White,
                edgePadding = 2.dp,
                backgroundColor = Color.Red,
                divider = {
                    Divider()
                },
                indicator = {

                },
            ) {
                tabs.forEachIndexed { tabIndex, tab ->
                    Tab(
                        selected = pagerState.currentPage == tabIndex,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.scrollToPage(tabIndex)
                            }
                        },
                        text = { Text(text = tab) }
                    )
                }
            }
        }


    }
}

@Composable
fun NewsPage(pageIndex: Int) {
    val lifecycleOwner = LocalLifecycleOwner.current
    XLogger.d("---------->NewsPage")

    val videoIds = mutableListOf(
        "6JYIGclVQdw",
        "LvetJ9U_tVY",
        "6JYIGclVQdw",
        "LvetJ9U_tVY",
        "6JYIGclVQdw",
        "LvetJ9U_tVY",
        "6JYIGclVQdw",
        "LvetJ9U_tVY",
        "6JYIGclVQdw",
        "LvetJ9U_tVY",
        "6JYIGclVQdw",
        "LvetJ9U_tVY"
    )

    val list = mutableListOf<VideoEntity>()

    repeat(50) {
        if (it % 4 == 0) {
            list.add(VideoEntity(isAd = true, type = YoutubeListAdapter.ITEM_TYPE_AD))
        } else if (it % 5 == 0) {
            list.add(
                VideoEntity(
                    videoId = videoIds.removeFirst(),
                    type = YoutubeListAdapter.ITEM_TYPE_VIDEO
                )
            )
        } else if (it % 3 == 0) {
            list.add(
                VideoEntity(
                    text = "pageIndex:$pageIndex News Title is $it",
                    des = "普京愿意要和乌坐下来谈？泽连斯基发声，中方立场或见效",
                    type = YoutubeListAdapter.ITEM_TYPE_THREE_PIC
                )
            )
        } else if (it % 7 == 0) {
            list.add(
                VideoEntity(
                    text = "pageIndex:$pageIndex News Title is $it",
                    des = "普京愿意要和乌坐下来谈？泽连斯基发声，中方立场或见效",
                    type = YoutubeListAdapter.ITEM_TYPE_BIG_PIC
                )
            )
        } else {
            list.add(
                VideoEntity(
                    text = "pageIndex:$pageIndex News Title is $it",
                    des = "普京愿意要和乌坐下来谈？泽连斯基发声，中方立场或见效",
                    type = YoutubeListAdapter.ITEM_TYPE_TEXT
                )
            )
        }
    }

    AndroidView(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xE9E9E7E7))
        .padding(start = 8.dp, end = 8.dp), factory = { context ->
        XLogger.d("---------->AndroidView")
        val binding = DataBindingUtil.inflate<ActivityYoutubeListRvBinding>(
            LayoutInflater.from(context), R.layout.activity_youtube_list_rv, null, false
        )
        binding.rv.setHasFixedSize(true)

        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        binding.rv.layoutManager = mLayoutManager

        val youtubeListAdapter = YoutubeListAdapter(list, lifecycleOwner.lifecycle)
        binding.rv.adapter = youtubeListAdapter
        binding.root
    })
}
