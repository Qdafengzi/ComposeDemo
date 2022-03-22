package com.example.composedemo.ui.page.anim

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar

/**
 * Created by finn on 2022/3/22
 */
@Composable
fun MoreText(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        var expand by remember { mutableStateOf(false) }
        val endText = if (expand) "  Less" else "...More"
        val minLine = 2
        val text =
            "动画在现代移动应用中至关重要，其目的是实现自然流畅、易于理解的用户体验。许多 Jetpack Compose 动画 API 可以提供可组合函数，就像布局和其他界面元素一样；它们由使用 Kotlin 协程挂起函数构建的较低级别 API 提供支持。本指南将首先介绍可用于许多实际场景的高级别 API，接着介绍可为您提供进一步控制和自定义功能的低级别 API。"
//        val text =
//            "等哈哈哈哈哈"
        //如果小于 minLine
        var lessLine by remember {
            mutableStateOf(false)
        }


        Column(Modifier.padding(16.dp)) {
            var content by remember {
                mutableStateOf(text)
            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray, shape = RoundedCornerShape(9.dp))
                    .animateContentSize()
            ) {
                val annotatedText = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append(content)
                    }
                    if(!lessLine){
                        pushStringAnnotation(
                            tag = "click",
                            annotation = "moreText"
                        )
                        withStyle(style = SpanStyle(color = Color.Blue)) {
                            append(endText)
                        }
                    }
                }

                ClickableText(
                    text = annotatedText,
                    modifier = Modifier.padding(16.dp),
                    maxLines = if (expand) Int.MAX_VALUE else minLine,
                    onTextLayout = { textLayoutResult ->
                        Log.d("截取前", "${textLayoutResult.lineCount} $expand")
                        if(textLayoutResult.lineCount<minLine){
                            lessLine= true
                        }

                        if (textLayoutResult.lineCount == minLine && !expand) {
                            val hasVisualOverflow = textLayoutResult.hasVisualOverflow
                            Log.d("截取前", "$hasVisualOverflow")
                            if (hasVisualOverflow) {
                                val lastCharIndex =
                                    textLayoutResult.getLineEnd(minLine - 1, true)
                                //截取 Less状态的内容
                                val substring = annotatedText.substring(0, lastCharIndex)
                                Log.d("截取前", substring)
                                content =
                                    substring.substring(0, substring.length - endText.length)
                                Log.d("截取后", content)
                            }
                        } else if (textLayoutResult.lineCount > minLine) {
                            Log.d("textLayout", "${textLayoutResult.lineCount}")
                            Log.d("textLayout", "${textLayoutResult.size}")
                            content = text
                            val lastCharIndex = textLayoutResult.getLineEnd(minLine - 1, true)
                            //截取前两行的内容
                            val substring = annotatedText.substring(0, lastCharIndex)
                            Log.d("textLayout", "---->$substring")
                        }
                    },
                    onClick = { offset ->
                        annotatedText.getStringAnnotations(start = offset, end = offset)
                            .firstOrNull()?.let { annotation ->
                                expand = !expand
                                if (expand) {
                                    content = text
                                }
                                Log.d("Click", "--click--->${annotation.item}")
                            }
                    }
                )
            }
        }
    }
}

@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    minimizedMaxLines: Int = 1,
) {
    var cutText by remember(text) { mutableStateOf<String?>(null) }
    var expanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    val seeMoreSizeState = remember { mutableStateOf<IntSize?>(null) }
    val seeMoreOffsetState = remember { mutableStateOf<Offset?>(null) }

    // getting raw values for smart cast
    val textLayoutResult = textLayoutResultState.value
    val seeMoreSize = seeMoreSizeState.value
    val seeMoreOffset = seeMoreOffsetState.value

    LaunchedEffect(text, expanded, textLayoutResult, seeMoreSize) {
        val lastLineIndex = minimizedMaxLines - 1
        if (!expanded && textLayoutResult != null && seeMoreSize != null
            && lastLineIndex + 1 == textLayoutResult.lineCount
            && textLayoutResult.isLineEllipsized(lastLineIndex)
        ) {
            var lastCharIndex = textLayoutResult.getLineEnd(lastLineIndex, visibleEnd = true) + 1
            var charRect: Rect
            do {
                lastCharIndex -= 1
                charRect = textLayoutResult.getCursorRect(lastCharIndex)
            } while (
                charRect.left > textLayoutResult.size.width - seeMoreSize.width
            )
            seeMoreOffsetState.value = Offset(charRect.left, charRect.bottom - seeMoreSize.height)
            cutText = text.substring(startIndex = 0, endIndex = lastCharIndex)
        } else {
            //seeMoreOffsetState.value = Offset(charRect.left, charRect.bottom - seeMoreSize.height)
        }
    }

    Box(modifier) {
        Text(
            text = cutText ?: text,
            maxLines = if (expanded) Int.MAX_VALUE else minimizedMaxLines,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResultState.value = it },
        )

        val density = LocalDensity.current
        Text(
            text = if (expanded) "...Shrink" else "... Expand",
            onTextLayout = { seeMoreSizeState.value = it.size },
            modifier = Modifier
                .then(
                    if (seeMoreOffset != null) {
                        Modifier.offset(
                            x = with(density) { seeMoreOffset.x.toDp() },
                            y = with(density) { seeMoreOffset.y.toDp() },
                        )
                    } else {
                        Modifier
                    }
                )
                .clickable {
                    expanded = !expanded
                    cutText = null
                }
            //.alpha(if (seeMoreOffset != null) 1f else 0f)
        )
    }
}

@Composable
fun MeasureTextWidth(content: @Composable (width: Dp) -> Unit) {
    SubcomposeLayout { constraints ->
        val textWidth = subcompose("sampleText") {
            Text(text = "哈哈哈哈哈哈哈哈")
        }[0].measure(Constraints()).width.toDp()

        val contentPlaceable = subcompose("content") {
            content(textWidth)
        }[0].measure(constraints)

        layout(contentPlaceable.width, contentPlaceable.height) {
            contentPlaceable.place(0, 0)
        }
    }
}