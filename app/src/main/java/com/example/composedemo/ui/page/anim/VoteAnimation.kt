package com.example.composedemo.ui.page.anim

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composedemo.app.Constant
import com.example.composedemo.data.VoteEntity
import com.example.composedemo.ui.theme.gradient1
import com.example.composedemo.ui.widget.CommonToolbar

/**
 * Created by finn on 2022/8/24
 */

@Composable
fun VoteAnimation(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        VoteContent()
    }
}


@OptIn(ExperimentalTextApi::class)
@Composable
fun VoteContent() {
    val dataList = Constant.getVoteList()

    var showPercent by remember {
        mutableStateOf(false)
    }


    Column(modifier = Modifier.fillMaxSize()) {
        Text(modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
            text = "中国颜值最高大学排名：TOP10名校扎堆，谁才是你心中的No.1 ?",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Blue,
            style = TextStyle(
                brush = Brush.linearGradient(
                    colors = gradient1
                )
            )
            )

        dataList.forEachIndexed { index, _ ->
            VoteItem(dataList[index], showPercent) {
                showPercent = true
            }
        }
    }
}


/**
 * 投票的每个条目的内容
 */
@Composable
fun VoteItem(voteEntity: VoteEntity, showPercent: Boolean, onClick: () -> Unit) {
    val shape = RoundedCornerShape(4.dp)
    val modifierOuter = Modifier
        .padding(horizontal = 20.dp, vertical = 4.dp)
        .fillMaxWidth()
        .clickable { onClick.invoke() }
        .height(30.dp)
        .background(color = Color(0xFFC5C1C1), shape = shape)

    Box(modifier = modifierOuter, contentAlignment = Alignment.CenterStart) {
        Box(modifier = Modifier
//                .animateContentSize(animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing))
            .animateContentSize(animationSpec = spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessVeryLow))
            .fillMaxWidth(if (showPercent) voteEntity.percent else 0.0f)
            .fillMaxHeight()
            .background(color = voteEntity.color, shape = shape), contentAlignment = Alignment.CenterEnd) {
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            VoteText(voteEntity.voteText)
            if (showPercent) {
                VotePercent(text = "${voteEntity.percent}")
            }
        }
    }
}

/**
 * 投票的选项内容
 */
@Composable
fun VoteText(text: String) {
    Text(modifier = Modifier.padding(start = 4.dp),
        text = text,
        color = Color.Black,
        fontSize = 14.sp)
}

/**
 * 投票的百分比
 */
@Composable
fun VotePercent(text: String) {
    Text(modifier = Modifier.padding(end = 4.dp),
        text = "$text%",
        color = Color.White,
        fontSize = 14.sp)
}