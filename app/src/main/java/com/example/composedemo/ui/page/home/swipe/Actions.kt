package com.example.composedemo.ui.page.home.swipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedemo.ui.theme.DeleteActionColor
import com.example.composedemo.ui.theme.EditActionColor
import com.example.composedemo.ui.theme.SaveActionColor

@Composable
fun SaveAction(modifier: Modifier) {
    Box(
        modifier = modifier.background(color = SaveActionColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 4.dp)
                    .padding(horizontal = 20.dp)
                    .size(22.dp),
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Color.White
            )

            Text(
                text = "Save",
                color = Color.White,
                fontSize = 12.sp,
            )
        }
    }

}


@Composable
fun EditAction(modifier: Modifier) {
    Box(
        modifier = modifier.background(EditActionColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 4.dp)
                    .padding(horizontal = 20.dp)
                    .size(22.dp),
                imageVector = Icons.Filled.Edit,
                contentDescription = null,
                tint = Color.White
            )

            Text(
                text = "Edit",
                color = Color.White,
                fontSize = 12.sp,
            )
        }
    }

}

@Composable
fun DeleteAction(modifier: Modifier) {
    Box(
        modifier = modifier.background(DeleteActionColor),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 4.dp)
                    .padding(horizontal = 20.dp)
                    .size(22.dp),
                imageVector = Icons.Filled.Delete,
                contentDescription = null,
                tint = Color.White
            )

            Text(
                text = "Delete",
                color = Color.White,
                fontSize = 12.sp,
            )
        }
    }

}