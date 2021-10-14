package com.example.composedemo.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.example.composedemo.R

class XmlAndComposeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_compose)
        val mLlContainer = findViewById<ComposeView>(R.id.mComposeView)
        mLlContainer.setContent {
            ComposeContent()
        }
    }


    @Composable
    fun ComposeContent() {
        LazyColumn(modifier = Modifier.fillMaxWidth(), content = {
            items(count = 10) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp), text = "Compose List Item $it"
                )
            }
        })
    }
}