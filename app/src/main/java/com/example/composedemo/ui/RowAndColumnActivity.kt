package com.example.composedemo.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class RowAndColumnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column() {
                Column(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .background(Color.Green),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = "Hello")
                    Text(text = "World")
                    Text(text = "Fill")
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .background(Color.Green),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Hello", modifier = Modifier.padding(end = 10.dp))
                    Text(text = "World", modifier = Modifier.padding(end = 10.dp))
                    Text(text = "Fill", modifier = Modifier.padding(end = 10.dp))
                }

                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .width(300.dp)
                        .height(200.dp)
                        .background(Color.Green),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Hello", modifier = Modifier.padding(end = 10.dp))
                    Text(text = "World", modifier = Modifier.padding(end = 10.dp))
                    Text(text = "Fill", modifier = Modifier.padding(end = 10.dp))
                }
            }
        }
    }
}