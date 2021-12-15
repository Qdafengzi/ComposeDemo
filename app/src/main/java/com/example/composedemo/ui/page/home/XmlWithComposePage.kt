package com.example.composedemo.ui.page.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar


@Composable
fun XmlWithComposePage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        Column() {
            Button(onClick = {


            }) {

            }
        }
    }
}