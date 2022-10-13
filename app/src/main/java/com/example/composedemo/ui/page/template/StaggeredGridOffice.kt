package com.example.composedemo.ui.page.template

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composedemo.R
import com.example.composedemo.ui.widget.CommonToolbar


data class StaggeredGridData(
    val name: String,
    val height: Int
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StaggeredGridOffice(navCtrl: NavHostController, title: String) {
    val list = mutableListOf<StaggeredGridData>()
    repeat(200) {
        list.add(StaggeredGridData(name = "name:$it", height = (200..300).random()))
    }

    CommonToolbar(navCtrl, title) {
        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2), content = {
            list.forEachIndexed { index, staggeredGridData ->
                item {
                    Column(
                        modifier = Modifier
                            .padding(vertical = 4.dp, horizontal = 9.dp)
                            .height(staggeredGridData.height.dp)
                            .fillMaxWidth()
                            .background(color = Color.Magenta, shape = RoundedCornerShape(9.dp)),
                    ) {
                        Image(
                            painter = painterResource(id = R.mipmap.img_10),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .padding(2.dp)
                                .fillMaxWidth()
                                .aspectRatio(4 / 3f)
                                .clip(RoundedCornerShape(9.dp))
                        )

                        Text(text = staggeredGridData.name, color = Color.Black, fontSize = 16.sp)
                    }
                }
            }
        })
    }
}