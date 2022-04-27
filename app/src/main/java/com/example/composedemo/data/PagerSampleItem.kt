package com.example.composedemo.data

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi

@OptIn(ExperimentalCoilApi::class)
@Composable
internal fun PagerSampleItem(
    page: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .background(Color.Blue)
            .fillMaxWidth()
            .aspectRatio(4 / 3f),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = page.toString(),
            modifier = Modifier
                .padding(16.dp)
                .background(MaterialTheme.colors.surface, RoundedCornerShape(4.dp))
                .sizeIn(minWidth = 40.dp, minHeight = 40.dp)
                .padding(8.dp)
                .wrapContentSize(Alignment.Center)
        )
    }
}