
package com.example.composedemo.ui.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.PagingDataDiffer
import androidx.paging.compose.LazyPagingItems
import kotlinx.coroutines.flow.Flow

@ExperimentalFoundationApi
interface LazyStaggeredGridScope {
    /**
     * Adds a single item to the scope.
     *
     * @param content the content of the item
     */
    fun item(key: Any? = null, content: @Composable LazyItemScope.() -> Unit)

    /**
     * Adds a [count] of items.
     *
     * @param count the items count
     * @param itemContent the content displayed by a single item
     */
    fun items(count: Int, key: ((index: Int) -> Any)? = null, itemContent: @Composable LazyItemScope.(index: Int) -> Unit)
}

/**
 * Adds a list of items.
 *
 * @param items the data list
 * @param itemContent the content displayed by a single item
 */
@ExperimentalFoundationApi
inline fun <T> LazyStaggeredGridScope.items(
    items: List<T>,
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit
) = items(items.size) {
    itemContent(items[it])
}

/**
 * Adds a list of items where the content of an item is aware of its index.
 *
 * @param items the data list
 * @param itemContent the content displayed by a single item
 */
@ExperimentalFoundationApi
inline fun <T> LazyStaggeredGridScope.itemsIndexed(
    items: List<T>,
    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit
) = items(items.size) {
    itemContent(it, items[it])
}

/**
 * Adds an array of items.
 *
 * @param items the data array
 * @param itemContent the content displayed by a single item
 */
@ExperimentalFoundationApi
inline fun <T> LazyStaggeredGridScope.items(
    items: Array<T>,
    crossinline itemContent: @Composable LazyItemScope.(item: T) -> Unit
) = items(items.size) {
    itemContent(items[it])
}

/**
 * Adds an array of items where the content of an item is aware of its index.
 *
 * @param items the data list
 * @param itemContent the content displayed by a single item
 */
@ExperimentalFoundationApi
inline fun <T> LazyStaggeredGridScope.itemsIndexed(
    items: Array<T>,
    crossinline itemContent: @Composable LazyItemScope.(index: Int, item: T) -> Unit
) = items(items.size) {
    itemContent(it, items[it])
}

/**
 * Adds the [LazyPagingItems] and their content to the scope. The range from 0 (inclusive) to
 * [LazyPagingItems.itemCount] (exclusive) always represents the full range of presentable items,
 * because every event from [PagingDataDiffer] will trigger a recomposition.
 *
 *
 * @param lazyPagingItems the items received from a [Flow] of [PagingData].
 * @param itemContent the content displayed by a single item. In case the item is `null`, the
 * [itemContent] method should handle the logic of displaying a placeholder instead of the main
 * content displayed by an item which is not `null`.
 */
@ExperimentalFoundationApi
inline fun <T : Any> LazyStaggeredGridScope.items(
    lazyPagingItems: LazyPagingItems<T>,
    crossinline itemContent: @Composable LazyItemScope.(value: T?) -> Unit
) = items(lazyPagingItems.itemCount) { index ->

//    itemContent(lazyPagingItems.getAsState(index).value)
    itemContent(  lazyPagingItems.peek(index))
}

/**
 * Adds the [LazyPagingItems] and their content to the scope where the content of an item is
 * aware of its local index. The range from 0 (inclusive) to [LazyPagingItems.itemCount] (exclusive)
 * always represents the full range of presentable items, because every event from
 * [PagingDataDiffer] will trigger a recomposition.
 *
 *
 * @param lazyPagingItems the items received from a [Flow] of [PagingData].
 * @param itemContent the content displayed by a single item. In case the item is `null`, the
 * [itemContent] method should handle the logic of displaying a placeholder instead of the main
 * content displayed by an item which is not `null`.
 */
@ExperimentalFoundationApi
inline fun <T : Any> LazyStaggeredGridScope.itemsIndexed(
    lazyPagingItems: LazyPagingItems<T>,
    crossinline itemContent: @Composable LazyItemScope.(index: Int, value: T?) -> Unit
) {
    items(lazyPagingItems.itemCount) { index ->
//        itemContent(index, lazyPagingItems.getAsState(index).value)
        itemContent(index,   lazyPagingItems.peek(index))
    }
}


@ExperimentalFoundationApi
@Composable
fun LazyStaggeredVerticalGrid(
    cells: GridCells,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: LazyStaggeredGridScope.() -> Unit,
    headContent: @Composable () -> Unit
) {
    val scope = LazyStaggeredVerticalGridImpl()
    scope.apply(content)

    when (cells) {
        is GridCells.Fixed ->
            BoxWithConstraints(
                modifier = modifier
            ) {
                FixedStaggeredVerticalLazyGrid(
                    nColumns = cells.count,
                    maxColumnWidth = maxWidth,
                    state = state,
                    contentPadding = contentPadding,
                    scope = scope,
                    headContent = headContent
                )
            }
        is GridCells.Adaptive ->
            BoxWithConstraints(
                modifier = modifier
            ) {
                val nColumns = maxOf((maxWidth / cells.minSize).toInt(), 1)
                FixedStaggeredVerticalLazyGrid(
                    nColumns = nColumns,
                    maxColumnWidth = maxWidth,
                    state = state,
                    contentPadding = contentPadding,
                    scope = scope,
                    headContent = headContent
                )
            }
    }
}

@ExperimentalFoundationApi
@Composable
private fun FixedStaggeredVerticalLazyGrid(
    nColumns: Int,
    maxColumnWidth: Dp,
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    scope: LazyStaggeredVerticalGridImpl,
    headContent: @Composable () -> Unit
) {
    val rows = (scope.totalSize + nColumns - 1) / nColumns
    LazyColumn(
        modifier = modifier,
        state = state,
        contentPadding = contentPadding
    ) {
        item {
            Box(modifier = Modifier.fillMaxWidth()) {
                headContent()
            }
        }
        item {
            Row {
                for (columnIndex in 0 until nColumns) {
                    Column {
                        for (rowIndex in 0 until rows) {
                            val itemIndex = rowIndex * nColumns + columnIndex
                            if (itemIndex < scope.totalSize) {
                                Box(modifier = Modifier.width(maxColumnWidth / nColumns)) {
                                    scope.contentFor(itemIndex, this@item).invoke()
                                }
                            } else {
                                Spacer(modifier = Modifier.weight(1f, fill = true))
                            }
                        }
                    }
                }
            }
        }
    }
}

private class IntervalContent(
    val key: ((index: Int) -> Any)?,
    val content: LazyItemScope.(index: Int) -> @Composable() () -> Unit
)

@ExperimentalFoundationApi
class LazyStaggeredVerticalGridImpl : LazyStaggeredGridScope {
    private val intervals = IntervalList<IntervalContent>()
    val totalSize get() = intervals.totalSize

    fun contentFor(index: Int, scope: LazyItemScope): @Composable () -> Unit {
        val interval = intervals.intervalForIndex(index)
        val localIntervalIndex = index - interval.startIndex
        return interval.content.content.invoke(scope, localIntervalIndex)
    }

    override fun item(key: Any?, content: @Composable LazyItemScope.() -> Unit) {
        intervals.add(
            1,
            IntervalContent(
                key = if (key != null) { _: Int -> key } else null,
                content = { @Composable { content() } }
            )
        )
    }

    override fun items(count: Int, key: ((index: Int) -> Any)?, itemContent: @Composable LazyItemScope.(index: Int) -> Unit) {
        intervals.add(
            count,
            IntervalContent(
                key = key,
                content = { index -> @Composable { itemContent(index) } }
            )
        )
    }
}

internal class IntervalHolder<T>(
    val startIndex: Int,
    val size: Int,
    val content: T
)

internal class IntervalList<T> {
    private val intervals = mutableListOf<IntervalHolder<T>>()
    internal var totalSize = 0
        private set

    fun add(size: Int, content: T) {
        if (size == 0) {
            return
        }

        val interval = IntervalHolder(
            startIndex = totalSize,
            size = size,
            content = content
        )
        totalSize += size
        intervals.add(interval)
    }

    fun intervalForIndex(index: Int) =
        if (index < 0 || index >= totalSize) {
            throw IndexOutOfBoundsException("Index $index, size $totalSize")
        } else {
            intervals[findIndexOfHighestValueLesserThan(intervals, index)]
        }

    /**
     * Finds the index of the [list] which contains the highest value of [IntervalHolder.startIndex]
     * that is less than or equal to the given [value].
     */
    private fun findIndexOfHighestValueLesserThan(list: List<IntervalHolder<T>>, value: Int): Int {
        var left = 0
        var right = list.lastIndex

        while (left < right) {
            val middle = (left + right) / 2

            val middleValue = list[middle].startIndex
            if (middleValue == value) {
                return middle
            }

            if (middleValue < value) {
                left = middle + 1
                // Verify that the left will not be bigger than our value
                if (value < list[left].startIndex) {
                    return middle
                }
            } else {
                right = middle - 1
            }
        }

        return left
    }
}