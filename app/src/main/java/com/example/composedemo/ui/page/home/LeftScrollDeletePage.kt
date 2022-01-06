package com.example.composedemo.ui.page.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.R
import com.example.composedemo.ui.widget.*
import kotlinx.coroutines.launch


@Composable
fun LeftScrollDeletePage(navCtrl: NavHostController, title: String) {
    rememberLazyListState()
    val list = remember {
        mutableStateListOf<String>()
    }

    LaunchedEffect(Unit) {
        repeat(5) {
            list.add("item $it")
        }
    }


    CommonToolbar(navCtrl, title) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            itemsIndexed(list) { index, item ->
                RevealSwipeExample(list, item, index)
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun RevealSwipeExample(list: MutableList<String>, item: String, position: Int) {
    val context = LocalContext.current
    val state = rememberRevealState()
    val coroutine = rememberCoroutineScope()
    RevealSwipe(
        modifier = Modifier.padding(vertical = 5.dp),
        directions = setOf(
            RevealDirection.StartToEnd,
            RevealDirection.EndToStart
        ),
        hiddenContentStart = {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 25.dp)
                    .clickable {
                        Toast
                            .makeText(context, "收藏", Toast.LENGTH_SHORT)
                            .show()
                        coroutine.launch {
                            //删除之后要重置 否则会影响下面的状态
                            state.reset()
                        }
                    },
                imageVector = Icons.Outlined.Star,
                contentDescription = stringResource(id = R.string.star),
                tint = Color.White
            )
        },
        hiddenContentEnd = {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 25.dp)
                    .clickable {
                        list.removeAt(position)
                        coroutine.launch {
                            //删除之后要重置 否则会影响下面的状态
                            state.reset()
                        }
                    },
                imageVector = Icons.Outlined.Delete,
                contentDescription = stringResource(id = R.string.delete)
            )
        },
        closeOnContentClick = true,
        closeOnBackgroundClick = true,
        state = state,
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .requiredHeight(80.dp),
            backgroundColor = Color.Blue,
            shape = it,
        ) {
            Text(
                modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                text = item
            )
        }
    }
}