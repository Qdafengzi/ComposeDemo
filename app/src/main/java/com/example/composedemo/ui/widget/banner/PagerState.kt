package com.example.composedemo.ui.widget.banner

import androidx.compose.animation.core.Animatable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import com.example.composedemo.utils.XLogger
import kotlin.math.roundToInt

/**
 * This is a modified version of:
 * https://gist.github.com/adamp/07d468f4bcfe632670f305ce3734f511
 */
// I Added support for vertical direction as well.

class PagerState(
    currentPage: Int = 0,
    minPage: Int = 0,
    maxPage: Int = 0
) {
    private var _minPage by mutableStateOf(minPage)
    var minPage: Int
        get() = _minPage
        set(value) {
            _minPage = value.coerceAtMost(_maxPage)
            _currentPage = _currentPage.coerceIn(_minPage, _maxPage)
        }

    private var _maxPage by mutableStateOf(maxPage)
    var maxPage: Int
        get() = _maxPage
        set(value) {
            _maxPage = value.coerceAtLeast(_minPage)
            _currentPage = _currentPage.coerceIn(_minPage, maxPage)
        }

    private var _currentPage by mutableStateOf(currentPage.coerceIn(minPage, maxPage))
    var currentPage: Int
        get() = _currentPage
        set(value) {
            _currentPage = value.coerceIn(minPage, maxPage)
        }

    var selectionState by mutableStateOf(SelectionState.Selected)

    suspend inline fun <R> selectPage(block: PagerState.() -> R): R = try {
        selectionState = SelectionState.Undecided
        block()
    } finally {
        selectPage()
    }

    suspend fun selectPage() {
        currentPage -= currentPageOffset.roundToInt()
        snapToOffset(0f)
        selectionState = SelectionState.Selected
    }

    private var _currentPageOffset = Animatable(0f).apply {
        updateBounds(-1f, 1f)
    }
    val currentPageOffset: Float
        get() = _currentPageOffset.value

    suspend fun snapToOffset(offset: Float) {
        val max = if (currentPage == minPage) 0f else 1f
        val min = if (currentPage == maxPage) 0f else -1f
        XLogger.d("计算新的位置----------->max:$max  min:$min   snapTo:${offset.coerceIn(min, max)}")
        _currentPageOffset.snapTo(offset.coerceIn(min, max))
//        _currentPageOffset.snapTo(offset)
    }

    suspend fun fling(velocity: Float) {
        //如果速度小于0 并且已经滑动到了最右侧 则重置为第一页
        if (velocity < 0 && currentPage == maxPage) {
            currentPage = minPage
        } else if (velocity > 0 && currentPage == minPage) {
            //如果速度 小于0 并且已经滑动到第一页 则重置为最后一页
            currentPage = maxPage
        }

        XLogger.d("速度: velocity:$velocity  minPage:$minPage  maxPage:$maxPage currentPageOffset:$currentPageOffset ")
        _currentPageOffset.animateTo(currentPageOffset.roundToInt().toFloat())
        selectPage()
    }

    override fun toString(): String =
        "PagerState{minPage=$minPage, maxPage=$maxPage, " +
                "currentPage=$currentPage, currentPageOffset=$currentPageOffset}"
}
