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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.recyclerview.widget.RecyclerView

class GoodsComposeViewHolder(
    private val composeView: ComposeView
) : RecyclerView.ViewHolder(composeView) {
    fun bind(input: String,des:String) {
        composeView.setContent {
            Row(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RoundedCornerShape(9.dp))
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            ) {
                Image(
                    painter = painterResource(id = com.example.composedemo.R.drawable.sheep),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(4.dp))
                )

                Column(
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = input,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                        text = des,
                        fontSize = 14.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}
