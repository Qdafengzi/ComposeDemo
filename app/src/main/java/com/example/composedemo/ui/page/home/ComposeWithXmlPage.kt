package com.example.composedemo.ui.page.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.composedemo.R
import com.example.composedemo.ui.widget.CommonToolbar


@Composable
fun ComposeWithXmlPage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        ComposeContent()
    }
}


@SuppressLint("SetTextI18n")
@Composable
fun ComposeContent() {
    LazyColumn(modifier = Modifier.fillMaxWidth(), content = {
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color.Green),
                textAlign = TextAlign.Center,
                text = "Compose header",
            )
        }
        items(count = 6) {
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                factory = { ctx ->
                    val view =
                        LayoutInflater.from(ctx).inflate(R.layout.activity_compose_xml, null)
                    view.findViewById<TextView>(R.id.tvText).text = "Xml Item $it"
                    view
                },
            )
        }
        item {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(8.dp)
                    .background(color = Color.Yellow),
                textAlign = TextAlign.Center,
                text = "Compose Footer",
            )
        }
    })
}