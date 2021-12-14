package com.example.composedemo.ui.page.template

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.composedemo.R
import com.example.composedemo.ui.theme.AppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState


@ExperimentalPagerApi
@Composable
fun JingDongPage(navCtrl: NavHostController, title: String) {
    Scaffold(topBar = {
        TopAppBar(backgroundColor = AppTheme.colors.toolbarColor) {
            Icon(
                modifier = Modifier.clickable {
                    navCtrl.popBackStack()
                },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = AppTheme.colors.mainColor
            )
            Text(
                modifier = Modifier.padding(20.dp),
                text = title,
                color = AppTheme.colors.mainColor
            )
        }
    }) {
        Column() {
            Content()
        }
    }
}


@ExperimentalPagerApi
@Composable
fun Content() {
    val list1 = mutableListOf<String>()
    val list2 = mutableListOf<String>()

    repeat(20) {
        list1.add("item one $it")
    }

    repeat(20) {
        list2.add("item two $it")
    }


    val state = remember { mutableStateOf(0) }
    val titles = listOf("评论（0）", "参与（20）")
    LazyColumn(modifier = Modifier.fillMaxWidth(), content = {
        item {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 19.dp, end = 19.dp)) {

                Banner()

                Text(modifier = Modifier.padding(top = 20.dp),
                    text = "闲置冰箱，有人要出，199包邮哦，或者自取便宜10块，一个",
                    fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color.Black)
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                    text = "王嘉尔，1994年3月28日出生于香港，中国香港流行乐男歌手、音乐人、主持人、设计师、创意总监 。王嘉尔12岁参加全运会，获得人生第一枚击剑金牌；在个人运动生涯中拿下三枚亚洲冠军、三枚全国冠军、九枚国际和香港冠军。2014年以GOT7成员的身份出道；同年获得SBS演艺大赏综艺部门男子新人奖。2015年与何炅搭档主持脱口秀节目《拜托了冰箱》。2016年与何炅搭档主持综艺节目《透鲜滴星期天》；同年获得腾讯视频星光大赏年度综艺之星奖。",
                    fontWeight = FontWeight.Normal, fontSize = 14.sp, color = Color.Black)
            }
        }

        item {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 19.dp, top = 16.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable {
                            state.value = 0
                        }
                        .wrapContentWidth()
                ) {
                    Text(text = titles[0], color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(1.dp))
                    Box(
                        modifier = Modifier
                            .width(46.dp)
                            .height(4.dp)
                            .background(color = if (state.value == 0) Color.Red else Color.Transparent, shape = RoundedCornerShape(2.dp))
                    )
                }

                Spacer(modifier = Modifier.width(30.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable {
                            state.value = 1
                        }
                        .wrapContentWidth()) {
                    Text(text = titles[1], color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(1.dp))
                    Box(modifier = Modifier
                        .width(46.dp)
                        .height(4.dp)
                        .background(color = if (state.value == 1) Color.Red else Color.Transparent, shape = RoundedCornerShape(2.dp))
                    )
                }
            }
        }

        if (state.value == 0) {
            items(list1.size) { position ->
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color.DarkGray)) {
                    Text(text = list1[position], modifier = Modifier.fillMaxWidth())
                }
            }
        } else {
            items(list2.size) { position ->
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color.LightGray)) {
                    Text(text = list2[position], modifier = Modifier.fillMaxWidth())
                }
            }
        }
    })
}


@ExperimentalPagerApi
@Composable
fun Banner() {
    val pagerState = rememberPagerState()
    val paintRes = painterResource(id = R.drawable.sheep)

    val size = 5

    ConstraintLayout() {
        val (horizontalPagerRefs, indicatorRes) = createRefs()
        HorizontalPager(
            count = size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(horizontalPagerRefs) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                },
        ) { page ->
            Log.d("TAG", "page:$page")
            Box(modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f, matchHeightConstraintsFirst = true)
                .background(color = Color.Red)) {
                Image(modifier = Modifier
                    .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    painter = paintRes,
                    contentDescription = null)
            }
        }

        Box(modifier = Modifier
            .padding(end = 9.dp, bottom = 9.dp)
            .wrapContentWidth()
            .height(24.dp)
            .constrainAs(indicatorRes) {
                end.linkTo(horizontalPagerRefs.end)
                bottom.linkTo(horizontalPagerRefs.bottom)
            }
        ) {
            Text(modifier = Modifier
                .background(color = Color.Black, shape = RoundedCornerShape(corner = CornerSize(12.dp)))
                .padding(start = 11.dp, end = 11.dp, top = 3.dp, bottom = 3.dp),
                text = "${pagerState.currentPage + 1}/$size", color = Color.White, fontSize = 13.sp)
        }
    }
}