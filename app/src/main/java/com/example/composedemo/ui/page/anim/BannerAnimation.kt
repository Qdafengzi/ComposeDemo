package com.example.composedemo.ui.page.anim

import android.view.MotionEvent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composedemo.ui.widget.CommonToolbar
import com.example.composedemo.utils.XLogger
import kotlinx.coroutines.launch
import kotlin.concurrent.fixedRateTimer


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BannerAnimation(navCtrl: NavHostController, title: String) {
    val images = listOf(
        "https://www.wanandroid.com/blogimgs/42da12d8-de56-4439-b40c-eab66c227a4b.png",
        "https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png",
        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
        "https://www.wanandroid.com/blogimgs/b1bd944a-4a9e-4722-81c5-079676422c5e.jpg",
        "https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png",
    )

    CommonToolbar(navCtrl, title) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            AutoSlidingCarousel(
                itemsCount = images.size,
                itemContent = { index ->
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(images[index])
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.height(200.dp)
                    )
                }
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun AutoSlidingCarousel(
    modifier: Modifier = Modifier,
    autoSlideDuration: Long = 3000,
    pagerState: PagerState = remember { PagerState() },
    itemsCount: Int,
    itemContent: @Composable (index: Int) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    var touchDown by remember {
        mutableStateOf(false)
    }
    val timer = remember {
        //创建一个定时器 周期性的执行
        fixedRateTimer(
            initialDelay = autoSlideDuration,
            period = autoSlideDuration
        ) {
            coroutineScope.launch {
                XLogger.d("========>周期执行的任务")
                //如果在触摸 就停止自动
                if (!touchDown) {
                    pagerState.animateScrollToPage((pagerState.currentPage + 1) % itemsCount)
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose { timer.cancel() }
    }

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        HorizontalPager(
            modifier = Modifier
                .pointerInteropFilter {
                    if(it.action == MotionEvent.ACTION_DOWN){
                        XLogger.d("=========>ACTION_DOWN")

                        false
                    }else if (it.action == MotionEvent.ACTION_UP){
                        XLogger.d("=========>ACTION_UP")

                        false
                    }
                    false
                }
//                .pointerInput(Unit){
//                    while (true){
//                        awaitPointerEventScope {
//                            awaitFirstDown(requireUnconsumed = true)
//                            // ACTION_DOWN here
//                            touchDown = true
//                            XLogger.d("=======>ACTION_DOWN")
//                            do {
//                                //This PointerEvent contains details including
//                                // event, id, position and more
//                                val event: PointerEvent = awaitPointerEvent()
//                                // ACTION_MOVE loop
//                                // Consuming event prevents other gestures or scroll to intercept
//                                event.changes.forEach { pointerInputChange: PointerInputChange ->
//                                    XLogger.d("===========>event.changes.forEach")
//                                }
//                            } while (event.changes.any { it.pressed })
//                            // ACTION_UP is here
//                            XLogger.d("=======>ACTION_UP")
//                            touchDown = false
//                        }
//                    }
//                }
            , pageCount = itemsCount, state = pagerState) { page ->
            itemContent(page)
        }
        /* Surface(
             modifier = Modifier
                 .padding(bottom = 8.dp)
                 .align(Alignment.BottomCenter),
             shape = CircleShape,
             color = Color.Black.copy(alpha = 0.5f)
         ) {
             DotsIndicator(
                 modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                 totalDots = itemsCount,
                 selectedIndex = pagerState.targetPage,
                 dotSize = 8.dp
             )
         }*/


        DotsIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 8.dp, vertical = 6.dp),
            totalDots = itemsCount,
            selectedIndex = pagerState.targetPage,
            dotSize = 8.dp
        )
    }
}

@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = Color.Green,
    unSelectedColor: Color = Color.Gray,
    dotSize: Dp
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) { index ->
            IndicatorDot(
                color = if (index == selectedIndex) selectedColor else unSelectedColor,
                size = dotSize
            )

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Composable
fun IndicatorDot(
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}