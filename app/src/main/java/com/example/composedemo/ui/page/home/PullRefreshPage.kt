package com.example.composedemo.ui.page.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animate
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullRefreshPage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        val refreshScope = rememberCoroutineScope()
        val threshold = with(LocalDensity.current) { 160.dp.toPx() }

        var refreshing by remember { mutableStateOf(false) }
        var itemCount by remember { mutableStateOf(15) }
        var currentDistance by remember { mutableStateOf(0f) }

        val progress = currentDistance / threshold

        fun refresh() = refreshScope.launch {
            refreshing = true
            delay(1500)
            itemCount += 5
            refreshing = false
        }

        fun onPull(pullDelta: Float): Float = when {
            refreshing -> 0f
            else -> {
                val newOffset = (currentDistance + pullDelta).coerceAtLeast(0f)
                val dragConsumed = newOffset - currentDistance
                currentDistance = newOffset
                dragConsumed
            }
        }

        suspend fun onRelease() {
            if (refreshing) return // Already refreshing - don't call refresh again.
            if (currentDistance > threshold) refresh()

            animate(initialValue = currentDistance, targetValue = 0f) { value, _ ->
                currentDistance = value
            }
        }

        Column(Modifier.pullRefresh(::onPull, {
            onRelease()
            0f
        })) {
            AnimatedVisibility(modifier = Modifier.wrapContentHeight(),visible = (refreshing || progress > 0)) {
                if (refreshing) {
                    Column(modifier = Modifier.wrapContentHeight()) {
                        LinearProgressIndicator(Modifier.fillMaxWidth())
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Text(text = "可以自定义了 刷新时间：12：22：22")
                        }
                    }
                } else {
                    LinearProgressIndicator(progress, Modifier.fillMaxWidth())
                }
            }

            LazyColumn {
                items(itemCount) {
                    ListItem { Text(text = "Item ${itemCount - it}") }
                }
            }

            // Custom progress indicator

        }
    }
}