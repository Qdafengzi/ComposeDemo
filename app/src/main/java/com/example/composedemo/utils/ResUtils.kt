package com.example.composedemo.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.DisplayMetrics
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.example.composedemo.app.App
import java.util.*




object ResUtils {


    fun getColor(@ColorRes colorRes: Int): Int {
        return ContextCompat.getColor(App.CONTEXT, colorRes)
    }

    fun getString(@StringRes stringRes: Int): String {
        return App.CONTEXT.getString(stringRes)
    }

    fun getString(@StringRes stringRes: Int, from: Any?): String {
        return App.CONTEXT.getString(stringRes, from)
    }

    fun getString(@StringRes id: Int, vararg formatArgs: Any?): String {
        return App.CONTEXT.getString(id, *formatArgs)
    }

    fun getDrawable(@DrawableRes drawableRes: Int): Drawable? {
        return ContextCompat.getDrawable(Objects.requireNonNull(App.CONTEXT), drawableRes)
    }

    fun getDimensPx(id: Int): Int {
        return App.CONTEXT.resources.getDimensionPixelSize(id)
    }

    val res: Resources
        get() = App.CONTEXT.resources

    fun getTypeFace(font: String): Typeface {
        return Typeface.createFromAsset(App.CONTEXT.assets, "font/$font.ttf")
    }

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    fun dp2px(dpValue: Float): Int {
        //获取手机的屏幕的密度
        val scale = res.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dp(pxValue: Float): Int {
        val scale = res.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * px   sp
     */
    fun px2sp(pxValue: Float): Int {
        val fontScale = res.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    fun sp2px(spValue: Float): Int {
        val fontScale = res.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * 宽和高
     */
    val screenWidth: Int
        get() = Resources.getSystem().displayMetrics.widthPixels
    val screenHeight: Int
        get() =  Resources.getSystem().displayMetrics.heightPixels

    fun getDisplay(activity: Activity): DisplayMetrics {
        val outMetrics = DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val display = activity.display
            display?.getRealMetrics(outMetrics)
        } else {
            @Suppress("DEPRECATION")
            val display = activity.windowManager.defaultDisplay
            @Suppress("DEPRECATION")
            display.getMetrics(outMetrics)
        }
        return outMetrics
    }


    fun getScreenWidth(context: Context): Int {
        val resources: Resources = context.resources
        val dm: DisplayMetrics = resources.displayMetrics
        return dm.widthPixels
    }
}