package com.example.composedemo.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivityNewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeNavigation()
        }
    }


    @Composable
    fun ComposeNavigation() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "first_screen"
        ) {
            composable("first_screen") {
                FirstScreen(navController = navController)
            }
            composable("second_screen") {
                SecondScreen(navController = navController)
            }
            composable("third_screen") {
                ThirdScreen(navController = navController)
            }
        }
    }


    @Composable
    fun FirstScreen(navController: NavController) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "First Screen\n" +
                        "Click me to go to Second Screen",
                color = Color.Black, fontSize = 30.sp,
                style = TextStyle(textAlign = TextAlign.Center),
                modifier = Modifier
                    .padding(24.dp)
                    .clickable(onClick = {
                        // this will navigate to second screen
                        navController.navigate("second_screen")
                    })
            )

            var text by remember { mutableStateOf("") }
            var isError by rememberSaveable { mutableStateOf(false) }
            fun validate(text: String) {
                isError = text.count() < 8
            }
            //普通输入框
            TextField(
                value = text,                               //显示的文本内容
                onValueChange = { text = it },                  //变化后的回调
                placeholder = {
                    Text(
                        text = "请输入密码",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                },    //文本提示占位符，类似Tint的效果
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .background(Color.White),                     //修饰符
                singleLine = true,                          //是否开启单行
                maxLines = 1,                               //最大行数
                //输入框后面的图标
                enabled = true,                             //是否可用，如果为false，将不可选中，不可输入，呈现出禁用状态
                readOnly = false,                            //是否只读，如果是true，则不可编辑，但是可以选中，可以触发复制
                textStyle = TextStyle.Default,              //文本样式，和Text的TextStyle是一样的
                label = {
                    Text(if (isError) "长度不能少于8位" else "请输入密码")
                },                                          //显示在文本字段内的可选标签
                isError = isError,                          //输入内容是否错误，如果为true，则label，Icon等会相应的展示错误的显示状态
                visualTransformation = PasswordVisualTransformation(),       //内容显示转变，例如输入密码时可以变成特定效果
                keyboardActions = KeyboardActions {
                    validate(text)
                },                                          //ImeAction
                keyboardOptions = KeyboardOptions.Default,  //软件键盘选项
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black                 //光标颜色
                )
            )
            //各种状态下的颜色
        }
    }

    @Composable
    fun SecondScreen(navController: NavController) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Second Screen\n" +
                        "Click me to go to Third Screen",
                color = Color.Black, fontSize = 30.sp,
                style = TextStyle(textAlign = TextAlign.Center),
                modifier = Modifier.clickable(onClick = {
                    // this will navigate to third screen
                    navController.navigate("third_screen")
                })
            )
        }
    }

    @Composable
    fun ThirdScreen(navController: NavController) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Third Screen\n" +
                        "Click me to go to First Screen",
                color = Color.Black, fontSize = 30.sp,
                style = TextStyle(textAlign = TextAlign.Center),
                modifier = Modifier.clickable(onClick = {
                    // this will navigate to first screen
                    navController.navigate("first_screen")
                })
            )
        }
    }


    @Composable
    fun NavigationComponent(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                HomeScreen(navController)
            }
            composable("details") {
                DetailScreen()
            }
        }
    }

    @Composable
    fun HomeScreen(navController: NavController) {
        Button(onClick = { navController.navigate("detail") }) {
            Text(text = "Go to detail")
        }
    }

    @Composable
    fun DetailScreen() {
        Text(text = "Detail")
    }
}

