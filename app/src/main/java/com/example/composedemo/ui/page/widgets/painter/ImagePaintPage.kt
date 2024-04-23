package com.example.composedemo.ui.page.widgets.painter

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar


@RequiresApi(Build.VERSION_CODES.R)
@ExperimentalComposeUiApi
@Composable
fun ImagePaintPage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
//        ImagePainter()
        ImagePainter()


//        val imgBitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.img_2)
//        DrawingImage(imgBitmap.asImageBitmap())
    }
}