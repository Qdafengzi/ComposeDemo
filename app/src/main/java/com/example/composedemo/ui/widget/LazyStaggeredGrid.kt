package com.example.composedemo.ui.widget

import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LazyStaggeredGrid(
    columnCount: Int,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable LazyStaggeredGridScopes.() -> Unit,
) {
    val states: Array<LazyListState> = (0 until columnCount)
        .map { rememberLazyListState() }
        .toTypedArray()
    val scope = rememberCoroutineScope { Dispatchers.Main.immediate }
    val scroll = rememberScrollableState { delta ->
        scope.launch { states.forEach { it.scrollBy(-delta) } }
        delta
    }
    val gridScope = LazyStaggeredGridScopes(columnCount)
    content(gridScope)

    Box(
        modifier = Modifier
            .scrollable(
                scroll,
                Orientation.Vertical,
                flingBehavior = ScrollableDefaults.flingBehavior()
            )
    ) {
        Row {
            for (index in 0 until columnCount) {
                LazyColumn(
                    contentPadding = contentPadding,
                    state = states[index],
                    modifier = Modifier.weight(1f)
                ) {
                    for ((key, itemContent) in gridScope.items[index]) {
                        item(key = key) {
                            itemContent()
                        }
                    }
                }
            }
        }
    }
}

class LazyStaggeredGridScopes(
    private val columnCount: Int,
) {

    var currentIndex = 0
    val items: Array<MutableList<Pair<Any?, @Composable () -> Unit>>> =
        Array(columnCount) { mutableListOf() }

    fun item(key: Any? = null, content: @Composable () -> Unit) {
        items[currentIndex % columnCount] += key to content
        currentIndex += 1
    }
}