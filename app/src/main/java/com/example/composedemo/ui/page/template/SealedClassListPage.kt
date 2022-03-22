package com.example.composedemo.ui.page.template

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composedemo.data.SealedClassEntity
import com.example.composedemo.ui.widget.CommonToolbar

/**
 * 用于多类型 数据模型 首页等可用
 * Created by finn on 2022/3/21
 */
@Composable
fun SealedClassListPage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        MultipleListDemo()
    }
}

@Composable
fun MultipleListDemo() {
    val list = mutableListOf<SealedClassEntity>()

    list.add(SealedClassEntity.SealedClass1(name = "sealed class 1 type"))
    list.add(SealedClassEntity.SealedClass2(name = "sealed class 2 type"))
    list.add(SealedClassEntity.SealedClass3(name = "sealed class 3 type"))
    list.add(SealedClassEntity.SealedClass1(name = "sealed class 1 type"))
    list.add(SealedClassEntity.SealedClass2(name = "sealed class 2 type"))
    list.add(SealedClassEntity.SealedClass3(name = "sealed class 3 type"))
    list.add(SealedClassEntity.SealedClass1(name = "sealed class 1 type"))
    list.add(SealedClassEntity.SealedClass2(name = "sealed class 2 type"))
    list.add(SealedClassEntity.SealedClass3(name = "sealed class 3 type"))


    LazyColumn(content = {
        items(count = list.size) {
            val data = list[it]
            var name: String
            val color = when (data) {
                is SealedClassEntity.SealedClass1 -> {
                    name = data.name
                    Color.Red
                }
                is SealedClassEntity.SealedClass2 -> {
                    name = data.name
                    Color.Blue
                }
                is SealedClassEntity.SealedClass3 -> {
                    name = data.name
                    Color.Magenta
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .height(100.dp).background(color = color)
            ) {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
        }
    })
}