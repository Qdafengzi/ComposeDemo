package com.example.composedemo.ui.widget

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun ItemButton(text: String, modifier: Modifier = Modifier, onclick: () -> Unit) {
    val color = Color((0..255).random(), (0..255).random(), (0..255).random())
    Button(modifier = modifier
        .wrapContentWidth(),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = color, contentColor = color),
        onClick = {
            onclick.invoke()
        }) {
        Text(text = text, color = Color.Black, textAlign = TextAlign.Center)
    }
}