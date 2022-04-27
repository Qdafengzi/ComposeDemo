package com.example.composedemo.ui.widget.banner

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import com.example.composedemo.utils.XLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


private val Measurable.page: Int
    get() = (parentData as? PageData)?.page ?: error("no PageData for measurable $this")

@Composable
fun Pager(
    modifier: Modifier = Modifier,
    pageState: PagerState,
    orientation: Orientation = Orientation.Horizontal,
    offscreenLimit: Int = 2,
    enableScroll: Boolean = true,
    content: @Composable PagerScope.() -> Unit
) {
    //页面的宽度 或者高度
    var pageRecSize by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    Layout(
        content = {
            //内容
            pageState.PrepareContent(offscreenLimit = offscreenLimit) {
                content.invoke(this)
            }
        },
        modifier = modifier.draggable(
            orientation = Orientation.Horizontal,
            enabled = enableScroll,
            onDragStarted = {
                pageState.selectionState = SelectionState.Undecided
            },
            onDragStopped = { velocity ->
                coroutineScope.launch {
                    /**
                    Velocity is in pixels per second, but we deal in percentage offsets, so we
                    need to scale the velocity to match
                     **/
                    //速度以每秒像素为单位，但我们处理百分比偏移量，因此我们需要缩放速度以匹配
                    pageState.fling(velocity / pageRecSize)
                }
            },
            state = rememberDraggableState { deltaInPx ->
                coroutineScope.calculateNewPosition(
                    deltaInPx,
                    pageState,
                    pageRecSize,
                    offscreenLimit
                )
            },
        )
    ) { measurables, constraints ->
        XLogger.d("--------->measurePolicy")
        layout(constraints.maxWidth, constraints.maxHeight) {
            val currentPage = pageState.currentPage
            val offset = pageState.currentPageOffset
            val childConstraints = constraints.copy(minWidth = 0, minHeight = 0)
            //测量策略
            measurables
                // placeables 是经过测量的子元素，它拥有自身的尺寸值
                .map {
                    // 测量所有子元素，这里不编写任何自定义测量逻辑，只是简单地
                    // 调用 Measurable 的 measure 函数并传入 constraints
                    it.measure(childConstraints) to it.page
                }
                .forEach { (placeable, page) ->
                    /**
                    current this centers each page. We should investigate reading
                    gravity modifiers on the child, or maybe as a param to Pager.
                     */
                    // current this 使每个页面居中。我们应该研究在孩子身上阅读重力修饰符，或者作为 Pager 的参数
                    val xCenterOffset = (constraints.maxWidth - placeable.width) / 2
                    val yCenterOffset = (constraints.maxHeight - placeable.height) / 2

                    if (currentPage == page) {
                        pageRecSize = if (orientation == Orientation.Horizontal) {
                            placeable.width
                        } else {
                            placeable.height
                        }
                    }
                    if (orientation == Orientation.Horizontal) {
                        // 通过遍历将每个项目放置到最终的预期位置

//                        placeable.placeRelative(
                        placeable.place(
                            x = xCenterOffset + ((page - (currentPage - offset)) * placeable.width).roundToInt(),
                            y = yCenterOffset
                        )
                    } else {
                        placeable.placeRelative(
                            x = xCenterOffset,
                            y = yCenterOffset + ((page - (currentPage - offset)) * placeable.height).roundToInt()
                        )
                    }
                }
        }
    }
}

@Composable
private fun PagerState.PrepareContent(
    offscreenLimit: Int,
    content: @Composable PagerScope.() -> Unit
) {
    val minPage = (currentPage - offscreenLimit).coerceAtLeast(minPage)
    val maxPage = (currentPage + offscreenLimit).coerceAtMost(maxPage)

    for (index in minPage..maxPage) {
        val pageData = PageData(index)
        val scope = PagerScope(this, index)
        XLogger.d("------>index:$index  pageData:${pageData.page}   scope comingPage:${scope.comingPage}   currentPage:${scope.currentPage}  currentPageOffset:${scope.currentPageOffset} selectionState:${scope.selectionState}")
        key(pageData) {
            Column(modifier = pageData) {
                scope.content()
            }
        }
    }
}

private fun CoroutineScope.calculateNewPosition(
    deltaInPx: Float,
    state: PagerState,
    pageRecSize: Int,
    offscreenLimit: Int
) {
    launch {
        with(state) {
            XLogger.d("计算新的位置 ")
            val pos = pageRecSize * currentPageOffset
            val max = if (currentPage == minPage) 0 else pageRecSize * offscreenLimit
            val min = if (currentPage == maxPage) 0 else -pageRecSize * offscreenLimit
            val newPos = (pos + deltaInPx).coerceIn(min.toFloat(), max.toFloat())
            XLogger.d(
                """
                计算新的位置
                  "delta:$deltaInPx  pageSize:$pageRecSize   offscreenLimit:$offscreenLimit"
                  "pos:$pos max:$max min:$min (pos + delta):${pos + deltaInPx} newPos:$newPos"
            """.trimIndent()
            )
            snapToOffset(newPos / pageRecSize)
        }
    }
}