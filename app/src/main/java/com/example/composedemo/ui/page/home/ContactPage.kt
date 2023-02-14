package com.example.composedemo.ui.page.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitDragOrCancellation
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


data class ContactEntity(
    val letter: Char,
    val name: String,
    val color: Color
)

/**
 * 获取联系人数据
 */
fun getContactData(): MutableList<ContactEntity> {
    val contactList = mutableListOf<ContactEntity>()
    (65..90).forEach { letter ->
//        val random = (5..20).random()
        val random = 5
        repeat(random) { index ->
            contactList.add(
                ContactEntity(
                    letter = letter.toChar(), name = "联系人  $index",
                    color = Color(
                        red = (0..255).random(),
                        blue = (0..255).random(),
                        green = (0..255).random()
                    )
                )
            )
        }
    }
    return contactList
}

/**
 * 获取首字母列表
 */
fun getCharList(): MutableList<Char> {
    val charList = mutableListOf<Char>()
    (65..90).forEach { letter ->
        charList.add(letter.toChar())
    }
    return charList
}


@OptIn(ExperimentalTextApi::class)
@Composable
fun ContactPage(navCtrl: NavHostController, title: String) {
    //联系人数据
    val contactList = getContactData()
    //字母数据
    val charList = getCharList()

    val offsetY = with(LocalDensity.current) {
        20.dp.toPx()
    }

    val offsetX = with(LocalDensity.current) {
        25.dp.toPx()
    }

    val coroutineScope = rememberCoroutineScope()
    val textMeasure = rememberTextMeasurer()
    val state = rememberLazyListState()


    //触摸的位置
    var touchOffset by remember() {
        mutableStateOf(Offset.Zero)
    }
    //触摸到的上一次的字母下标
    var touchIndexLast by remember {
        mutableStateOf(-1)
    }

    //触摸的字母的下标
    val touchIndex = remember(touchOffset.y) {
        derivedStateOf {
            if (touchOffset.y > 0f) {
                //通过偏移的倍数计算滑动到哪个字母的位置了
                val y = (touchOffset.y / offsetY).roundToInt()
                if (y in 0..25) {
                    touchIndexLast = y
                    y
                } else {
                    touchIndexLast
                }
            } else touchIndexLast
        }
    }

    //触摸到的字符的index
    val touchLetterIndex = remember {
        derivedStateOf {
            if (state.isScrollInProgress && state.layoutInfo.visibleItemsInfo.isNotEmpty()) {
                val key = state.layoutInfo.visibleItemsInfo[0].key
                if (key is Int && key < contactList.size) {
                    val letter = contactList[key].letter
                    val findIndex = charList.indexOfFirst {
                        it == letter
                    }
                    findIndex
                } else {
                    touchIndex.value
                }
            } else {
                touchIndex.value
            }
        }
    }

    //上一次选择的letter
    var lastLetter by remember {
        mutableStateOf("")
    }

    //滑动到的letter
    val scrollLetter = remember {
        derivedStateOf {
            if (touchIndex.value > 0) {
                val letter = (65 + touchIndex.value).toChar()
                coroutineScope.launch {
                    //找到相应的item
                    val index = getIndex(contactList, letter)
                    state.scrollToItem(index, scrollOffset = 20)
                }
                val str = letter.toString()
                lastLetter = str
                str
            } else {
                lastLetter
            }
        }
    }

    CommonToolbar(navCtrl, title) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxWidth(), state = state, content = {
                contactList.forEachIndexed { index, contactEntity ->
                    item(key = index) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(CircleShape)
                                        .background(
                                            color = contactEntity.color,
                                        ), contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "${contactEntity.letter}",
                                        fontSize = 16.sp,
                                        color = Color.White,
                                        fontWeight = FontWeight.SemiBold,
                                    )
                                }

                                Text(
                                    text = contactEntity.name,
                                    modifier = Modifier
                                        .padding(20.dp)
                                        .weight(1f)
                                        .padding(horizontal = 10.dp, vertical = 16.dp)
                                )
                            }
                            Divider()
                        }
                    }
                }

                item {
                    Text(
                        text = "共${contactList.size}联系人",
                        fontSize = 12.sp,
                        color = Color(0xFF333333),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp),
                        textAlign = TextAlign.Center
                    )
                }
            })

            //绘制右侧的26个字母
            Canvas(modifier = Modifier
                .padding(top = 30.dp)
                .width(50.dp)
                .fillMaxHeight()
                .align(Alignment.TopEnd)
                .pointerInput(Unit) {
                    coroutineScope {
                        while (true) {
                            // down事件
                            val downPointerInputChange = awaitPointerEventScope {
                                awaitFirstDown()
                            }
                            // 如果位置不在手指按下的位置，先动画的形式过度到手指按下的位置
                            if (touchOffset.x != downPointerInputChange.position.x && touchOffset.y != downPointerInputChange.position.y) {
                                launch {
                                    touchOffset = downPointerInputChange.position
                                }
                            }

                            // touch Move事件
                            // 滑动的时候，box随着手指的移动去移动
                            awaitPointerEventScope {
                                drag(downPointerInputChange.id, onDrag = {
                                    touchOffset = it.position
                                })
                            }

                            // 在手指弹起的时候，才通过动画的形式，回到原点的位置
                            val dragUpOrCancelPointerInputChange = awaitPointerEventScope {
                                awaitDragOrCancellation(downPointerInputChange.id)
                            }
                            // 等于空，说明已经抬起
                            if (dragUpOrCancelPointerInputChange == null) {
                                launch {
                                    touchOffset = Offset.Zero
                                }
                            }
                        }
                    }
                }, onDraw = {
                charList.forEachIndexed { index, char ->
                    drawText(
                        size = Size(width = offsetY, offsetY),
                        textMeasurer = textMeasure, text = "$char",
                        style = TextStyle(
                            fontWeight = if (touchLetterIndex.value == index) FontWeight.SemiBold else FontWeight.Medium,
                            color = if (touchLetterIndex.value == index) Color.Blue else Color(
                                0xFF333333
                            ),
                            textAlign = TextAlign.Center,
                            fontSize = if (touchLetterIndex.value == index) 16.sp else 14.sp
                        ),
                        topLeft = Offset(offsetX, offsetY * index),
                    )
                }
            })


            //中间显示的大写字母
            val textMeasurer1 = rememberTextMeasurer()
            if (touchOffset.x != 0f && touchOffset.y != 0f && scrollLetter.value.isNotEmpty()) {
                val annotatedString = AnnotatedString(
                    scrollLetter.value, spanStyle = SpanStyle(
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF333333),
                        fontSize = 20.sp,
                    )
                )

                val textLayoutResult = textMeasurer1.measure(text = annotatedString)
                val textSize = textLayoutResult.size


                Canvas(modifier = Modifier
                    .align(Alignment.Center)
                    .requiredSize(width = 50.dp, height = 50.dp), onDraw = {
                    //底部颜色
                    drawRoundRect(
                        color = Color(0xA403A9F4), cornerRadius = CornerRadius(10f, 10f)
                    )
                    //绘制字母
                    drawText(
                        textMeasurer = textMeasurer1,
                        text = annotatedString,
                        topLeft = Offset(
                            (size.width - textSize.width) / 2f,
                            (size.height - textSize.height) / 2f
                        )
                    )
                })
            }
        }
    }
}

private fun getIndex(list: MutableList<ContactEntity>, letter: Char): Int {
    val findIndex = list.indexOfFirst { contactEntity ->
        contactEntity.letter == letter
    }
    return findIndex
}