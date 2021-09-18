package com.example.composedemo.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = state, enabled = true)
            ) {

                CreateButton(clazz = RowAndColumnActivity::class.java, text = "Row And Column")
                CreateButton(clazz = ModifierActivity::class.java, text = "Modifier")
                CreateButton(clazz = ImageCardActivity::class.java, text = "Card And Image")
                CreateButton(clazz = FontActivity::class.java, text = "Font")
                CreateButton(clazz = StateActivity::class.java, text = "State")
                CreateButton(clazz = SnackBarActivity::class.java, text = "SnackBar")
                CreateButton(clazz = ListActivity::class.java, text = "List")
                CreateButton(clazz = ConstrainLayoutActivity::class.java, text = "ConstrainLayout")

                CreateButton(clazz = ButtonActivity::class.java, text = "Button")
                CreateButton(clazz = WebViewActivity::class.java, text = "WebView")
                CreateButton(clazz = GridListActivity::class.java, text = "Grid")
            }
        }
    }

    @Composable
    fun <T> CreateButton(clazz: Class<T>, text: String, modifier: Modifier = Modifier) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 18.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Button(modifier = modifier.fillMaxWidth(), onClick = {
                intent = Intent(this@MainActivity, clazz)
                startActivity(intent)
            }) {
                Text(text = text, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        }
    }
}
