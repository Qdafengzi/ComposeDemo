package com.example.composedemo.ui.page.anim

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composedemo.R
import com.example.composedemo.state.ScrollViewModel
import com.example.composedemo.ui.widget.CommonToolbar
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch

/**
 * 跑马灯
 * Created by finn on 2022/3/21
 */

val CustomEasing = Easing { fraction -> fraction * fraction }


@OptIn(
    ExperimentalAnimationApi::class, ExperimentalComposeUiApi::class,
    ExperimentalPagerApi::class
)
@Composable
fun MarqueePage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
//        Banner1()
//        AnimateIncrementDecrementExample()
//        Banner2()

        Row(modifier = Modifier.fillMaxSize()) {
            repeat(4) {
                Box(modifier = Modifier.offset(x = 10.dp)) {
                    Image(
                        painter = painterResource(id = R.mipmap.coil_icon1),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .border(width = 1.dp, color = Color.Red, CircleShape),
                    )
                }
            }
        }


//        val pagerState = rememberPagerState()
//        val count = 4
//        LaunchedEffect(key1 = pagerState.currentPage) {
//            launch {
//                delay(1500)
//                with(pagerState) {
//                    val target = if (currentPage < pageCount - 1) currentPage + 1 else 0
//                    animateScrollToPage(
//                        page = target,
//                        animationSpec =
//
//                        tween(
//                            durationMillis = if(target==0)10 else 500,
//                            easing = FastOutSlowInEasing
//                        )
//                    )
//                }
//            }
//
//
////            launch {
////                delay(2000)
////                with(pagerState) {
////                    val target = if (currentPage < count - 1) {
////                        currentPage + 1
////                    } else {
////                        0
////                    }
////                    if (target != 0) {
////                        pagerState.animateScrollToPage(targetPage)
////                    } else {
////                        pagerState.scrollToPage(target)
////                    }
////                }
////            }
//        }
//
//        Column {
//            HorizontalPager(
//                modifier = Modifier
//                    .weight(1f)
//                    .fillMaxWidth()
//                ,
//                count = count,
////                state = pagerState,
//
//                ) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .fillMaxHeight()
//                        .background(
//                            color = Color(
//                                red = (0..255).random(),
//                                blue = (0..255).random(),
//                                green = (0..255).random()
//                            )
//                        ),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(text = "------>${it}")
//                }
//
//            }
//        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Banner2(viewModel: ScrollViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.65f)
            .height(((24 + 8) * 4).dp)
    ) {
        repeat(4) {
            ItemMarquess(text = viewModel.list[it])
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Banner1(viewModel: ScrollViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val scope = rememberCoroutineScope()
    val list = viewModel.list

    val state = rememberLazyListState()


//    val value by animateFloatAsState(
//        targetValue = 100f,
//        animationSpec = infiniteRepeatable(
//            animation = tween(durationMillis = 300)
//        )
//    )

    var index = 1
    val px = LocalDensity.current.run { 32.dp.toPx() }
    val offset = animateIntOffsetAsState(targetValue = IntOffset(0, px.toInt()),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000)
        ), finishedListener = {
            Log.d("finish", "---->${it.y}}")
        }
    )


    LaunchedEffect(Unit) {


        scope.launch {
//                delay(2000)
//            index +=1
//            state.animateScrollToItem(index)
            state.animateScrollBy(
                px, infiniteRepeatable(
                    animation = tween(durationMillis = 1000)
                )
            )


        }
    }


    Log.d("--------", "0000000")
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(((24 + 8) * 4).dp)
                .offset(offset = {
                    offset.value
                })
                .pointerInteropFilter {
                    true
                },
//            state = state,
            content = {
//                items(count = if (list.size > 0) Int.MAX_VALUE else 0) { index ->
//                    val data = list[index % list.size]
                items(count = list.size) { index ->
                    val data = list[index]
                    Box(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, top = 8.dp)
                            .fillMaxWidth()
                            .height(24.dp)
                            .background(
                                color = Color.LightGray,
                                shape = RoundedCornerShape(9.dp)
                            ),
                    ) {
                        Text(
                            data,
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
            })
    }
}

@Composable
fun Banner3(viewModel: ScrollViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    viewModel.list
    repeat(4) {
        ItemMarquess(text = viewModel.list[it])
    }
}

@Composable
fun ItemMarquess(text: String) {
    Row(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 8.dp)
            .fillMaxWidth()
            .height(24.dp)
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(9.dp)
            ), verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 12.sp,
            color = Color.White
        )
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimateIncrementDecrementExample() {
    Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        var count by remember { mutableStateOf(0) }
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInVertically(initialOffsetY = { it }) + fadeIn() with slideOutVertically(
                        targetOffsetY = { -it }) + fadeOut()
                } else {
                    slideInVertically(initialOffsetY = { -it }) + fadeIn() with slideOutVertically(
                        targetOffsetY = { it }) + fadeOut()
                }.using(SizeTransform(clip = false))
            }
        ) { targetCount ->
            Text("$targetCount", fontSize = 20.sp)
        }
        Spacer(Modifier.size(20.dp))
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            Button(onClick = { count-- }) { Text(stringResource(id = R.string.minus)) }
            Spacer(Modifier.size(60.dp))
            Button(onClick = { count++ }) { Text(stringResource(id = R.string.plus)) }
        }
    }
}

private enum class CartState {
    Expanded,
    Collapsed
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimatedContentTransitionSpecExample() {
    var cartState by remember { mutableStateOf(CartState.Collapsed) }
    val transitionSpec: AnimatedContentScope<CartState>.() -> ContentTransform =
        {
            fadeIn(animationSpec = tween(150, delayMillis = 150))
                .with(fadeOut(animationSpec = tween(150)))
                .using(
                    SizeTransform { initialSize, targetSize ->
                        if (CartState.Collapsed isTransitioningTo CartState.Expanded) {
                            keyframes {
                                durationMillis = 500
                                IntSize(targetSize.width, initialSize.height + 200) at 150
                            }
                        } else {
                            keyframes {
                                durationMillis = 500
                                IntSize(
                                    initialSize.width,
                                    (initialSize.height + targetSize.height) / 2
                                ) at 150
                            }
                        }
                    }
                )
        }
//    AnimatedContent(
//        targetState = cartState,
//        transitionSpec = transitionSpec
//    ) { state ->
//        when (state) {
//            CartState.Expanded -> ExpandedCart {
//                cartState = CartState.Collapsed
//            }
//            CartState.Collapsed -> CollapsedCart {
//                cartState = CartState.Expanded
//            }
//        }
//    }
}
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//private fun ExpandedCart(
//    onClick: () -> Unit
//) {
//    val shoppingCart = stringResource(id = R.string.shopping_cart)
//    Card(
//        shape = CutCornerShape(12.dp),
//        onClick = onClick,
//        backgroundColor = MaterialTheme.colors.primary
//    ) {
//        Row(
//            modifier = Modifier.padding(16.dp)
//        ) {
//            Icon(
//                imageVector = Icons.Filled.AddShoppingCart,
//                contentDescription = shoppingCart
//            )
//            Text(
//                text = shoppingCart,
//            )
//        }
//    }
//}



