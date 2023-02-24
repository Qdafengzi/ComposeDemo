package com.example.composedemo.ui.page.template

import android.graphics.RenderEffect
import android.graphics.RenderNode
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.R
import com.example.composedemo.ui.widget.CommonToolbar
import com.example.composedemo.utils.XLogger


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun BlurPage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        val contentNode = RenderNode("image")
        val blurNode = RenderNode("blur")

        var showBlur by remember {
            mutableStateOf(false)
        }

        val animatedBlur by animateDpAsState(targetValue = if (showBlur) 8.dp else 0.dp,
            animationSpec = tween(1000),
            finishedListener = {
                XLogger.d("========>完成")
            })

        Image(
            contentScale = ContentScale.Fit,
            painter = painterResource(id = R.drawable.sheep),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .drawWithContent {
                    drawContent()

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        blurNode.setRenderEffect(
                            RenderEffect.createBlurEffect(
                                30f, 30f, Shader.TileMode.CLAMP
                            )
                        )
                    }

                    blurNode.setPosition(0, 0, this.size.width.toInt(), 100)
                    blurNode.translationY = this.size.height - 100f

                    val blurCanvas = blurNode.beginRecording()
                    blurCanvas.translate(0f, -(this.size.height - 100f))
                    blurCanvas.drawRenderNode(contentNode)
                    blurNode.endRecording()

                    drawContext.canvas.nativeCanvas.apply {
                        drawRenderNode(blurNode)
                    }
                },
        )


        Image(
            contentScale = ContentScale.Fit,
            painter = painterResource(id = R.drawable.sheep),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
//                .blur(radius = 10.dp, edgeTreatment = BlurredEdgeTreatment.Rectangle)
//                .blur(
//                    radiusX = 2.dp,
//                    radiusY = 2.dp,
//                    edgeTreatment = BlurredEdgeTreatment.Rectangle
//                )
//                .blur(radiusX = 10.dp, radiusY = 10.dp, edgeTreatment = BlurredEdgeTreatment.Rectangle)
                .blur(radius = animatedBlur)
        )

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp), onClick = {
            showBlur = !showBlur
        }) {

            val status = "高斯模糊状态:${if (showBlur) "关闭" else "打开"}"
            Text(text = status)
        }

    }
}