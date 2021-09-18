package com.example.composedemo.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.widget.VerticalGrid

class GridListActivity : AppCompatActivity() {

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GridList()
        }
    }


    @ExperimentalFoundationApi
    @Composable
    fun GridList() {
        Column() {
            VerticalGrid(columns = 3) {
                for (index in 1..100) {
                    Box(modifier = Modifier.background(color = Color.Green)) {
                        Text(text = "Text $index", modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp))
                    }
                }
            }
        }
    }
}