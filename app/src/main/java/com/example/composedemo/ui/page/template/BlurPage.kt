package com.example.composedemo.ui.page.template

import android.graphics.RenderEffect
import android.graphics.RenderNode
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.composedemo.R
import com.example.composedemo.ui.widget.CommonToolbar


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun BlurPage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        val contentNode = RenderNode("image")
        val blurNode = RenderNode("blur")
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
                            RenderEffect.createBlurEffect(30f, 30f,
                                Shader.TileMode.CLAMP))
                    }



                    blurNode.setPosition(0, 0, this.size.width.toInt(), 100)
                    blurNode.translationY =  this.size.height - 100f

                    val blurCanvas = blurNode.beginRecording()
                    blurCanvas.translate(0f, -(this.size.height - 100f))
                    blurCanvas.drawRenderNode(contentNode)
                    blurNode.endRecording()

                    drawContext.canvas.nativeCanvas.apply {
                        drawRenderNode(blurNode)
                    }
                },
        )
    }
}