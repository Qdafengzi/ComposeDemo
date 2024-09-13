package com.example.composedemo.ui.page.anim

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
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
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Refresh3Page(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        var itemCount by remember { mutableIntStateOf(15) }
        var isRefreshing by remember { mutableStateOf(false) }
        val coroutineScope = rememberCoroutineScope()
        val onRefresh: () -> Unit = {
            isRefreshing = true
            coroutineScope.launch {         // fetch something
                delay(1500)
                itemCount += 5
                isRefreshing = false
            }
        }
        val state = remember {
            object :
                PullToRefreshState {
                private val anim = Animatable(0f, Float.VectorConverter)
                override val distanceFraction
                    get() = anim.value

                override suspend fun animateToThreshold() {
                    anim.animateTo(
                        1f,
                        spring(dampingRatio = Spring.DampingRatioHighBouncy)
                    )
                }

                override suspend fun animateToHidden() {
                    anim.animateTo(0f)
                }

                override suspend fun snapTo(targetValue: Float) {
                    anim.snapTo(targetValue)
                }
            }
        }

        Scaffold(topBar = {
            TopAppBar(
                title = { Text("TopAppBar") },

                // Provide an accessible alternative to trigger refresh.
                actions = {
                    IconButton(onClick = onRefresh) {
                        Icon(
                            Icons.Filled.Refresh,
                            "Trigger Refresh"
                        )
                    }
                })
        }) {
            PullToRefreshBox(
                modifier = Modifier.padding(it),
                isRefreshing = isRefreshing,
                onRefresh = onRefresh,
                state = state
            ) {
                LazyColumn(Modifier.fillMaxSize()) {
                    if (!isRefreshing) {
                        items(itemCount) { ListItem({ Text(text = "Item ${itemCount - it}") }) }
                    }
                }
            }
        }
    }
}