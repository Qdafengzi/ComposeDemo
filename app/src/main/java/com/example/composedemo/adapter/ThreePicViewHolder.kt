package com.example.composedemo.adapter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.recyclerview.widget.RecyclerView
import com.example.composedemo.R

class ThreePicViewHolder(
    private val composeView: ComposeView
) : RecyclerView.ViewHolder(composeView) {
    fun bind(input: String) {
        composeView.setContent {
            Column(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(9.dp))
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = input,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.sheep),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .weight(1f)
                            .aspectRatio(4 / 3f)
                            .clip(RoundedCornerShape(4.dp))
                    )
                    Image(
                        painter = painterResource(id = R.drawable.sheep),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .weight(1f)
                            .aspectRatio(4 / 3f)
                            .clip(RoundedCornerShape(4.dp))
                    )
                    Image(
                        painter = painterResource(id = R.drawable.sheep),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .weight(1f)
                            .aspectRatio(4 / 3f)
                            .clip(RoundedCornerShape(4.dp))
                    )
                }
            }
        }
    }
}
