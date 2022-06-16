package com.example.composedemo.ui.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.AppTheme


@Composable
fun ItemButton(text: String, modifier: Modifier = Modifier, onclick: () -> Unit) {
    Button(modifier = modifier
        .padding(start = 20.dp, end = 20.dp, top = 10.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AppTheme.colors.toolbarColor,
            contentColor = AppTheme.colors.toolbarColor
        ),
        onClick = {
            onclick.invoke()
        }) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            text = text,
            color = Color.White,
            textAlign = TextAlign.Start
        )
    }
}