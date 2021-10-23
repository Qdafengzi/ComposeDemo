package com.example.composedemo.ui.activity

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.composedemo.R

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
class WechatFriendsCircleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FriendsCircle()
        }
    }

    @Preview
    @Composable
    fun FriendsCircle() {
        val screenWidth = getScreenWidth()
        var picWidth by remember { mutableStateOf(0) }

        LazyColumn(modifier = Modifier.fillMaxSize(), content = {
            items(count = 10) {
                ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                    val (headRef, titleRef, desRef, picsRef) = createRefs()
                    Image(
                        modifier = Modifier
                            .size(40.dp)
                            .padding(4.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.DarkGray, CircleShape)
                            .constrainAs(headRef) {
                                top.linkTo(parent.top, margin = 10.dp)
                                start.linkTo(parent.start, margin = 10.dp)
                            },
                        painter = painterResource(id = R.drawable.vector_empty),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier
                            .constrainAs(titleRef) {
                                top.linkTo(headRef.top)
                                start.linkTo(headRef.end, margin = 10.dp)
                                end.linkTo(parent.end, margin = 10.dp)
                                bottom.linkTo(headRef.bottom)
                                width = Dimension.fillToConstraints
                            },

                        text = "这是一个标题 $it",
                        textAlign = TextAlign.Start,
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                    )

                    Text(
                        modifier = Modifier.constrainAs(desRef) {
                            top.linkTo(headRef.bottom, margin = 10.dp)
                            start.linkTo(headRef.end, margin = 10.dp)
                            end.linkTo(parent.end, margin = 10.dp)
                            width = Dimension.fillToConstraints
                        },
                        text = "王嘉尔，1994年3月28日出生于香港，中国香港流行乐男歌手、音乐人、主持人、设计师、创意总监 。王嘉尔，1994年3月28日出生于香港，中国香港流行乐男歌手、音乐人、主持人、设计师、创意总监 王嘉尔，1994年3月28日出生于香港，中国香港流行乐男歌手、音乐人、主持人、设计师、创意总监 王嘉尔，1994年3月28日出生于香港，中国香港流行乐男歌手、音乐人、主持人、设计师、创意总监  $it"
                    )
                    val padding = (LocalDensity.current.run { 4.dp.toPx() }).toInt()

                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .onSizeChanged {
                            picWidth = it.width
                        }
                        .constrainAs(picsRef) {
                            top.linkTo(desRef.bottom, margin = 10.dp)
                            start.linkTo(headRef.end, margin = 10.dp)
                            end.linkTo(parent.end, margin = 10.dp)
                            width = Dimension.fillToConstraints
                        }
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            for (index in 0..2) {
                                PicItem(pxToDp(((picWidth - padding) / 3)).dp)
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            for (index in 0..2) {
                                PicItem(pxToDp(((picWidth - padding) / 3)).dp)
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            for (index in 0..2) {
                                PicItem(pxToDp(((picWidth - padding) / 3)).dp)
                            }
                        }
                    }


                    //稍微 有些卡 列表嵌套列表的形式
//                    val numbers = (0..8).toList()
//                    val pxValue = with(LocalDensity.current) { 10.dp.toPx() }
//                    val picWidth = (screenWidth - pxValue * 2) / 3
//
//                    LazyVerticalGrid(
//                        cells = GridCells.Fixed(3),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(pxToDp(picWidth.toInt() * 3).dp)
//                            .constrainAs(picsRef) {
//                                top.linkTo(desRef.bottom, margin = 10.dp)
//                                start.linkTo(headRef.end, margin = 10.dp)
//                                end.linkTo(parent.end, margin = 10.dp)
//                                width = Dimension.fillToConstraints
//                            }
//
//                    ) {
//                        items(numbers.size) {
//                            Image(
//                                modifier = Modifier
//                                    .height(pxToDp(picWidth.toInt()).dp)
//                                    .padding(horizontal = 2.dp)
//                                    .clip(RoundedCornerShape(corner = CornerSize(4.dp)))
//                                    .border(
//                                        1.dp,
//                                        Color.Gray,
//                                        RoundedCornerShape(corner = CornerSize(4.dp))
//                                    )
//                                    .aspectRatio(1f),
//                                painter = painterResource(id = R.drawable.vector_empty),
//                                contentDescription = null
//                            )
//                        }
//                    }
                }
            }
        })
    }

    @Composable
    fun PicItem(width: Dp) {
        Image(
            modifier = Modifier
                .width(width)
                .padding(horizontal = 2.dp)
                .clip(RoundedCornerShape(corner = CornerSize(4.dp)))
                .border(
                    1.dp,
                    Color.Gray,
                    RoundedCornerShape(corner = CornerSize(4.dp))
                )
                .aspectRatio(1f),
            painter = painterResource(id = R.drawable.vector_empty),
            contentDescription = null
        )
    }


    private fun pxToDp(px: Int): Int {
        return (px / Resources.getSystem().displayMetrics.density).toInt()
    }

    private fun getScreenWidth(): Int {
        return Resources.getSystem().displayMetrics?.widthPixels ?: 0
    }
}