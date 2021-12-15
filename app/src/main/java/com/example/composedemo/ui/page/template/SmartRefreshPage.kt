package com.example.composedemo.ui.page.template

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar


@Composable
fun SmartRefreshPage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        SmartRefreshLayout(content = {
            LazyColumn(content = {
                items(count = 10) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .background(color = Color.White),
                        text = "内容",
                        fontSize = 30.sp
                    )
                }
            })
        })
    }
}


@Composable
fun SmartRefreshLayout(maxHeight: Float = 300f, content: @Composable BoxScope.() -> Unit) {
    var headHeight by remember {
        mutableStateOf(0f)
    }



    Box(modifier = Modifier
        .fillMaxWidth()
        .nestedScroll(connection = MyNestedScrollConnection())
        .pointerInput(Unit) {
            detectDragGestures(
                onDragStart = { offset ->
                    // 拖动开始
                    headHeight = 0f
                },
                onDragEnd = {
                    // 拖动结束
                    headHeight = 0f
                    Log.d("---->onDragEnd", "onDragEnd")
                },
                onDragCancel = {
                    // 拖动取消
                    headHeight = 0f
                    Log.d("---->onDragCancel", "onDragCancel")
                },
                onDrag = { change: PointerInputChange, dragAmount: Offset ->
                    // 拖动中
                    if (headHeight > maxHeight) {
                        headHeight = maxHeight
                    } else {
                        headHeight += dragAmount.y
                    }
                }
            )
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(Dp(headHeight))
                .background(color = Color.Red)
        ) {
            Text(text = "刷新中", color = Color.White)
        }



        Box() {
            content()
        }
    }
}

class MyNestedScrollConnection : NestedScrollConnection {
    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {

        return super.onPostFling(consumed, available)
    }

    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        if (source == NestedScrollSource.Drag) {
            Log.d("onPreScroll---->", "available:${available.y}")
        }
        if (available.y == 0f) {
            return Offset(0f, 0f)
        }

        return super.onPreScroll(available, source)
    }

    override suspend fun onPreFling(available: Velocity): Velocity {

        return super.onPreFling(available)
    }

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource
    ): Offset {
        if (source == NestedScrollSource.Drag) {
            Log.d("onPostScroll---->", "consumed:${consumed.y} available${available.y}")


        }


        return super.onPostScroll(consumed, available, source)
    }

}