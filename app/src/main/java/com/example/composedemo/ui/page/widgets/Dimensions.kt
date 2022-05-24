package com.example.composedemo.ui.page.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import com.example.composedemo.utils.XLogger

/**
 * 不同尺寸适配
 * Created by finn on 2022/5/24
 */
class Dimensions {
    companion object{
        @Composable
        fun width(value:Float):Float{
            val config = LocalConfiguration.current
            return (value*config.screenWidthDp)/100
        }

        @Composable
        fun height(value:Float):Float{
            val config = LocalConfiguration.current
            return (value*config.screenHeightDp)/100
        }

        @Composable
        fun fontSize(value:Float):Float{
            val config = LocalConfiguration.current
            XLogger.d("----->fontScale:${config.fontScale}")
            return  value*config.fontScale
        }
    }
}