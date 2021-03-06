package com.example.composedemo.ui.page.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import kotlinx.coroutines.launch


@Composable
fun SnackBarPage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        Column() {
            val scaffoldState = rememberScaffoldState()
            //文本框的输入文字内容 默认""
            var textFieldState by remember {
                mutableStateOf("")
            }

            val scope = rememberCoroutineScope()

            //熟悉的名字 Flutter 上面的脚手架
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                scaffoldState = scaffoldState
            ) {
                //水平居中
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 30.dp)
                )
                {
                    TextField(value = textFieldState, label = {
                        Text("Please input your name")
                    }, onValueChange = {
                        textFieldState = it
                    },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Button(onClick = {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "You put text: $textFieldState"
                                )
                            }
                        }) {
                            Text("Commit")
                        }
                    }
                }
            }
        }
    }

}