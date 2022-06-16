package com.example.composedemo.ui.page.template

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar

/**
 * Created by finn on 2022/5/6
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridViewPage(navCtrl: NavHostController, title: String) {
    val list = mutableListOf<String>()
    (65..(65 + 25)).forEach {
        list.add(it.toChar().toString())
    }
    CommonToolbar(navCtrl, title) {
        androidx.compose.foundation.lazy.grid.LazyVerticalGrid(
            contentPadding = PaddingValues(horizontal = 4.dp),
            columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(3),
            content = {
                list.forEachIndexed() { _, item ->
                    item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(1) }) {
                        WordItem(word = item)
                    }
                }
            })
    }
}

@Composable
fun WordItem(word: String) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(color = Color.Magenta, shape = RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = word, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White)
    }
}