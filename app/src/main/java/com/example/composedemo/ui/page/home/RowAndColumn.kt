package com.example.composedemo.ui.page.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.theme.AppTheme


@Composable
fun RowAndColumn(navCtrl: NavHostController, title: String) {
    Scaffold(topBar = {
        TopAppBar(backgroundColor = AppTheme.colors.toolbarColor) {
            Icon(
                modifier = Modifier.clickable {
                    navCtrl.popBackStack()
                },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = AppTheme.colors.mainColor
            )
            Text(
                modifier = Modifier.padding(20.dp),
                text = title,
                color = AppTheme.colors.mainColor
            )
        }
    }) {
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