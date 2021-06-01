package com.example.composedemo.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedemo.R

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateItems()
        }
    }

    @Composable
    private fun CreateItemsOne(){
        val painter1 = painterResource(id = R.drawable.sheep)
        val painter2 = painterResource(id = R.drawable.ic_launcher_background)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp)
        ) {
            itemsIndexed(
                listOf(
                    "Android",
                    "Java",
                    "C",
                    "C++",
                    "Python",
                    "Go",
                    "Php",
                    "Kotlin",
                    "Flutter",
                    "C#",

                    "Android",
                    "Java",
                    "C",
                    "C++",
                    "Python",
                    "Go",
                    "Php",
                    "Kotlin",
                    "Flutter",
                    "C#",

                    "Android",
                    "Java",
                    "C",
                    "C++",
                    "Python",
                    "Go",
                    "Php",
                    "Kotlin",
                    "Flutter",
                    "C#",

                    "Android",
                    "Java",
                    "C",
                    "C++",
                    "Python",
                    "Go",
                    "Php",
                    "Kotlin",
                    "Flutter",
                    "C#",

                    ),

                ) { index: Int, item: String ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.Gray),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .height(100.dp)
                            .width(100.dp),
                        painter = if (index % 2 == 0) painter1 else painter2,
                        contentDescription = ""
                    )

                    Text(
                        text = " $item",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
        }
    }


    @Composable
    private fun CreateItems() {
        val painter1 = painterResource(id = R.drawable.sheep)
        val painter2 = painterResource(id = R.drawable.ic_launcher_background)
        LazyColumn() {
            items(200) {
                Column() {
                    Text(
                        text = "Item $it",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )

                    Image(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth(),
                        painter = if (it % 2 == 0) painter1 else painter2,
                        contentDescription = ""
                    )
                }
            }
        }
    }
}