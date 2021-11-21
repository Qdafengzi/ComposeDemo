package com.example.composedemo.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged


@InternalCoroutinesApi
class LazyColumnForIndexedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val list = mutableListOf<String>()
            repeat(5) {
                list.add("Item $it")
            }

            InfiniteList(list) {
                repeat(2) {
                    list.add("Item add $it")
                }
            }
        }
    }


    @Composable
    fun InfiniteList(
        listItems: List<String>,
        onLoadMore: () -> Unit
    ) {
        val listState = rememberLazyListState()

        LazyColumn(
            state = listState
        ) {
            items(listItems.size) { userItem ->
                Text(modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(color = Color.LightGray),text = "Item $userItem")
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        InfiniteListHandler(listState = listState) {
            onLoadMore()
        }
    }


    @Composable
    fun InfiniteListHandler(
        listState: LazyListState,
        buffer: Int = 2,
        onLoadMore: () -> Unit
    ) {
        val loadMore = remember {
            derivedStateOf {
                val layoutInfo = listState.layoutInfo
                val totalItemsNumber = layoutInfo.totalItemsCount
                val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1
                lastVisibleItemIndex > (totalItemsNumber - buffer)
            }
        }

        LaunchedEffect(key1 = loadMore, block = {
            snapshotFlow {
                loadMore.value
            }.distinctUntilChanged().collect {
                onLoadMore()
            }
        })
    }
}