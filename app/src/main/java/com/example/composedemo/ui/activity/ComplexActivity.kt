package com.example.composedemo.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ComplexActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumnDemo()
        }
    }


    @Composable
    fun LazyColumnDemo() {
        val listData = (0..20).toList()
        val childData = (0..10).toList()
        val selectedIndex = remember { mutableStateOf(0) }

        val tabsName = remember { DemoTabs.values().map { it.value } }

        LazyColumn(modifier = Modifier.fillMaxWidth(), content = {
            //头部内容
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.Cyan),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "头部")
                }
            }

            //列表内容
            items(count = listData.size) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(
                            if (it % 2 == 0) {
                                Color.Gray
                            } else {
                                Color.LightGray
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (it == 4) {
                        LazyColumn(modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),content = {
                            items(count = childData.size){content->
                                Box( modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .background(
                                        if (content % 2 == 0) {
                                            Color.Red
                                        } else {
                                            Color.Yellow
                                        }
                                    ),
                                    contentAlignment = Alignment.Center) {
                                    Text(text = "子item  $content")
                                }
                            }
                        })
                    } else if (it == 6) {
                        LazyRow(modifier = Modifier.fillMaxWidth(),content = {
                            items(count = childData.size){content->
                                Box( modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .background(
                                        if (content % 2 == 0) {
                                            Color.Red
                                        } else {
                                            Color.Yellow
                                        }
                                    ),
                                    contentAlignment = Alignment.Center) {
                                    Text(text = "子item  $content")
                                }
                            }
                        })
                    } else {
                        Text(text = "列表项：$it")
                    }

                }
            }

            //尾部内容
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.Magenta),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "尾部0")
                }
            }
            item {
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .fillParentMaxHeight()
                ){
                    TabRow(modifier = Modifier.height(50.dp),selectedTabIndex = selectedIndex.value) {
                        tabsName.forEachIndexed {index, title ->
                            Tab(
                                selected = index == selectedIndex.value,
                                onClick = {
                                    when (title) {
                                        DemoTabs.APPLE.value -> {
                                            selectedIndex.value = DemoTabs.APPLE.ordinal
                                        }
                                        DemoTabs.GOOGLE.value -> {
                                            selectedIndex.value = DemoTabs.GOOGLE.ordinal
                                        }
                                        DemoTabs.AMAZON.value -> {
                                            selectedIndex.value = DemoTabs.AMAZON.ordinal
                                        }
                                    }
                                },
                                text = { Text(title) }
                            )
                        }
                    }

                    Surface(modifier = Modifier.fillMaxHeight()) {
                        when (selectedIndex.value) {
                            DemoTabs.APPLE.ordinal -> {
                               Apple()
                            }
                            DemoTabs.GOOGLE.ordinal -> {
                                Google()
                            }
                            DemoTabs.AMAZON.ordinal -> {
                                Amazon()
                            }
                        }
                    }
                }
            }
        })
    }


    @Composable
    fun Apple(){
        LazyColumn(modifier = Modifier.fillMaxWidth(),content = {
            item(){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(color = Color.Yellow)){
                    Text(text = "1",fontSize = 35.sp)
                }
            }

            item(){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(color = Color.Red)){
                    Text(text = "2",fontSize = 35.sp)
                }
            }
            item(){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(color = Color.Green)){
                    Text(text = "3",fontSize = 35.sp)
                }
            }
        })
    }

    @Composable
    fun Google(){
        LazyColumn(modifier = Modifier.fillMaxWidth(),content = {
            item(){
                Box(modifier = Modifier
                    .height(400.dp)
                    .background(color = Color.Yellow)){
                    Text(text = "1",fontSize = 35.sp)
                }
            }

            item(){
                Box(modifier = Modifier
                    .height(400.dp)
                    .background(color = Color.Green)){
                    Text(text = "2",fontSize = 35.sp)
                }
            }
        })
    }

    @Composable
    fun Amazon(){
        LazyColumn(modifier = Modifier.fillMaxWidth(),content = {
            item(){
                Box(modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .background(color = Color.Yellow)){
                    Text(text = "1",fontSize = 35.sp)
                }
            }

            item(){
                Box(modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .background(color = Color.Red)){
                    Text(text = "2",fontSize = 35.sp)
                }
            }
            item(){
                Box(modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .background(color = Color.Green)){
                    Text(text = "3",fontSize = 35.sp)
                }
            }
            item(){
                Box(modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .background(color = Color.Yellow)){
                    Text(text = "4",fontSize = 35.sp)
                }
            }

            item(){
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(color = Color.Red)){
                    Text(text = "5",fontSize = 35.sp)
                }
            }
            item(){
                Box(modifier = Modifier
                    .height(400.dp)
                    .fillMaxWidth()
                    .background(color = Color.Green)){
                    Text(text = "6",fontSize = 35.sp)
                }
            }
        })
    }


    private enum class DemoTabs(val value: String) {
        APPLE("Apple"),
        GOOGLE("Google"),
        AMAZON("Amazon")
    }

}