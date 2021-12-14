package com.example.composedemo.ui.page.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composedemo.R
import com.example.composedemo.ui.theme.AppTheme


@Composable
fun ImageCardPage(navCtrl: NavHostController, title: String) {
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
        val painter = painterResource(id = R.drawable.sheep)
        val des = "this is android test"
        val title = "This is Title of image"
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(16.dp)
        ) {
            ShowImageCard(title = title, des = des, painter = painter)
        }
    }

}

@Composable
fun ShowImageCard(title: String, des: String, painter: Painter, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {

        Box(modifier = Modifier.height(200.dp)) {
            Image(
                painter = painter,
                contentDescription = des,
                contentScale = ContentScale.Crop
            )
            //拉渐变
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f//数据越大黑色越少
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.BottomStart
            )
            {
                Text(title, style = TextStyle(color = Color.White, fontSize = 16.sp))
            }
        }
    }
}
