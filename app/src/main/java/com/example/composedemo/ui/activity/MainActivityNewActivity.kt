package com.example.composedemo.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
            Scaffold() {
                ComposeNavigation()

            }
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
            //???????????????
            TextField(
                value = text,                               //?????????????????????
                onValueChange = { text = it },                  //??????????????????
                placeholder = {
                    Text(
                        text = "???????????????",
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                },    //??????????????????????????????Tint?????????
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .background(Color.White),                     //?????????
                singleLine = true,                          //??????????????????
                maxLines = 1,                               //????????????
                //????????????????????????
                enabled = true,                             //????????????????????????false?????????????????????????????????????????????????????????
                readOnly = false,                            //????????????????????????true????????????????????????????????????????????????????????????
                textStyle = TextStyle.Default,              //??????????????????Text???TextStyle????????????
                label = {
                    Text(if (isError) "??????????????????8???" else "???????????????")
                },                                          //???????????????????????????????????????
                isError = isError,                          //????????????????????????????????????true??????label???Icon??????????????????????????????????????????
                visualTransformation = PasswordVisualTransformation(),       //??????????????????????????????????????????????????????????????????
                keyboardActions = KeyboardActions {
                    validate(text)
                },                                          //ImeAction
                keyboardOptions = KeyboardOptions.Default,  //??????????????????
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.Black                 //????????????
                )
            )
            //????????????????????????
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

