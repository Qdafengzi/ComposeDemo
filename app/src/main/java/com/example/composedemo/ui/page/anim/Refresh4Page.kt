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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Refresh4Page(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        val viewModel = remember {
            object : ViewModel() {
                private val refreshRequests = Channel<Unit>(1)
                var isRefreshing by mutableStateOf(false)
                    private set
                var itemCount by mutableStateOf(15)
                    private set

                init {
                    viewModelScope.launch {
                        for (r in refreshRequests) {
                            isRefreshing = true
                        }
                        try {
                            itemCount += 5
                            delay(1000) // simulate doing real work
                        } finally {
                            isRefreshing = false
                        }
                    }
                }

                fun refresh() {
                    refreshRequests.trySend(Unit)
                }
            }
        }

        Scaffold(topBar = {
            TopAppBar(
                title = { Text("Title") },
                // Provide an accessible alternative to trigger refresh.
                actions = {
                    IconButton(
                        enabled = !viewModel.isRefreshing,
                        onClick = { viewModel.refresh() }) {
                        Icon(
                            Icons.Filled.Refresh,
                            "Trigger Refresh"
                        )
                    }
                })
        }) {
            PullToRefreshBox(
                modifier = Modifier.padding(it),
                isRefreshing = viewModel.isRefreshing,
                onRefresh = { viewModel.refresh() }) {
                LazyColumn(Modifier.fillMaxSize()) {
                    if (!viewModel.isRefreshing) {
                        items(viewModel.itemCount) { ListItem({ Text(text = "Item ${viewModel.itemCount - it}") }) }
                    }
                }
            }
        }
    }

}