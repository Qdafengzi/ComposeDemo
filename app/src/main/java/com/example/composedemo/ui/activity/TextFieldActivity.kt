package com.example.composedemo.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

class TextFieldActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .weight(1f, true)
                            .background(color = Color.Blue)
                    ) {
                        Text(text = "dsa;;sdklalk")
                    }

                    var text by remember {
                        mutableStateOf("")
                    }

                    TextField(
                        modifier = Modifier
                            .wrapContentWidth()
                            .background(color = Color.LightGray),
                        value = text,
                        onValueChange = {
                            text = it
                        },
                        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                        placeholder = {
                            Text(modifier = Modifier.wrapContentWidth(),text = "请输入联系人",color= Color.Gray,textAlign = TextAlign.End)
                        },

                        )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f, true)
                            .background(color = Color.Blue)
                    ) {
                        Text(text = "dsa;;sdklalk")
                    }


                    var text by remember {
                        mutableStateOf("")
                    }

                    BasicTextField(
                        modifier = Modifier
                            .background(color = Color.LightGray),
                        maxLines = 1,
                        value = text,
                        onValueChange = {
                            text = it
                        },
                        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
                        decorationBox = {
                            Text(text = "请输入联系人", modifier = Modifier.wrapContentWidth())
                        }

                    )
                }
            }
        }
    }
}