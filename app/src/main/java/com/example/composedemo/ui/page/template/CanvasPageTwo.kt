package com.example.composedemo.ui.page.template

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar

/**
 * Created by finn on 2022/6/24
 */

data class StockData(
    val x: Float,
    val y: Float,
)

@Composable
fun CanvasPageTwo(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        Column(modifier = Modifier.fillMaxSize()) {
            ChartOne()
            ChartTwo()


        }
    }
}

@Composable
fun ChartTwo() {


}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChartOne() {
    val list = mutableListOf<StockData>()
    list.add(StockData(10f, 20f))
    list.add(StockData(20f, 30f))
    list.add(StockData(40f, 30f))
    list.add(StockData(50f, 60f))
    list.add(StockData(80f, 180f))
    list.add(StockData(100f, 150f))
    list.add(StockData(130f, 100f))
    list.add(StockData(150f, 160f))
    list.add(StockData(160f, 130f))
    list.add(StockData(170f, 144f))
    list.add(StockData(180f, 120f))
    list.add(StockData(190f, 155f))
    list.add(StockData(220f, 150f))
    list.add(StockData(230f, 160f))

    val paddingHorizontalX = with(LocalDensity.current) { 20.dp.toPx() }
    val strokeWith = 4f
    val distanceY = with(LocalDensity.current) { 200.dp.toPx() }
    val drawColor = Color.Black
    Canvas(modifier = Modifier
        .fillMaxWidth(),
        contentDescription = "",
        onDraw = {
            val paint = android.graphics.Paint()
            paint.color = android.graphics.Color.BLACK
            paint.strokeWidth = 4f

            //画纵轴
            drawLine(color = drawColor,
                start = Offset(paddingHorizontalX, paddingHorizontalX),
                end = Offset(paddingHorizontalX, paddingHorizontalX + distanceY),
                strokeWidth = strokeWith)
            //纵轴刻度和数据
            for (index in (0..9)) {
                drawLine(color = drawColor,
                    start = Offset(paddingHorizontalX + 4f, paddingHorizontalX + (index * distanceY / 10f)),
                    end = Offset(paddingHorizontalX + 4f + 10f, paddingHorizontalX + (index * distanceY / 10f)),
                    strokeWidth = strokeWith / 2f
                )
                drawContext.canvas.nativeCanvas.apply {
                    drawText("${200 - 20 * index}",
                        paddingHorizontalX / 2f,
                        paddingHorizontalX + (index * distanceY / 10f) + 10f, paint)
                }
            }

            //画横轴
            drawLine(color = drawColor,
                start = Offset(paddingHorizontalX, paddingHorizontalX + distanceY),
                end = Offset(size.width - paddingHorizontalX, paddingHorizontalX + distanceY),
                strokeWidth = strokeWith)

            //横轴刻度和数据
            val horizontalInterval = (size.width - paddingHorizontalX * 2) / 10f
            for (index in (1..9)) {
                drawLine(color = drawColor,
                    start = Offset(paddingHorizontalX + index * horizontalInterval, paddingHorizontalX + distanceY - 10f),
                    end = Offset(paddingHorizontalX + index * horizontalInterval, paddingHorizontalX + distanceY),
                    strokeWidth = strokeWith / 2f
                )

                drawContext.canvas.nativeCanvas.apply {
                    drawText("${20 * index}",
                        paddingHorizontalX + index * horizontalInterval,
                        paddingHorizontalX + distanceY + paddingHorizontalX / 2f, paint)
                }
            }
            val path = Path()
            list.forEachIndexed { index, stockData ->
                if(index==0){
                    path.moveTo(paddingHorizontalX + (stockData.x/20f) * horizontalInterval, paddingHorizontalX+ distanceY-(stockData.y/20f)*distanceY/10f)
                }else {
                    path.lineTo(paddingHorizontalX + (stockData.x/20f) * horizontalInterval, paddingHorizontalX+distanceY-(stockData.y/20f)*distanceY/10f)
                }
            }
            drawPath(path = path, color = Color.Magenta, style = Stroke(width = 2f))
        })
}