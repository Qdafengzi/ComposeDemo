package com.example.composedemo.ui.page.anim

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Refresh2Page(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        var itemCount by remember { mutableStateOf(15) }
        var isRefreshing by remember { mutableStateOf(false) }
        val state = rememberPullToRefreshState()
        val coroutineScope = rememberCoroutineScope()
        val onRefresh: () -> Unit = {
            isRefreshing = true
            coroutineScope.launch {         // fetch something
                delay(1500)
                itemCount += 5
                isRefreshing = false
            }
        }
        val scaleFraction = {
            if (isRefreshing) 1f else LinearOutSlowInEasing.transform(state.distanceFraction)
                .coerceIn(0f, 1f)
        }
        Scaffold(
            modifier =
            Modifier.pullToRefresh(
                state = state,
                isRefreshing = isRefreshing,
                onRefresh = onRefresh
            ), topBar = {
                TopAppBar(
                    title = { Text("TopAppBar") },
                    // Provide an accessible alternative to trigger refresh.
                    actions = {
                        IconButton(onClick = onRefresh) {
                            Icon(
                                Icons.Filled.Refresh, "Trigger Refresh"
                            )
                        }
                    })
            }) {
            Box(Modifier.padding(it)) {
                LazyColumn(Modifier.fillMaxSize()) {
                    if (!isRefreshing) {
                        items(itemCount) { ListItem({ Text(text = "Item ${itemCount - it}") }) }
                    }
                }
                Box(
                    Modifier
                        .align(Alignment.TopCenter)
                        .graphicsLayer {
                            scaleX = scaleFraction()
                            scaleY = scaleFraction()
                        }) {
                    androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator(
                        state = state,
                        isRefreshing = isRefreshing
                    )
                }
            }
        }
    }
}