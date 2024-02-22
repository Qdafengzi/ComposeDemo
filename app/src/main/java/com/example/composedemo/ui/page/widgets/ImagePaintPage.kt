package com.example.composedemo.ui.page.widgets

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.composedemo.R
import com.example.composedemo.ui.widget.CommonToolbar
import com.example.composedemo.utils.XLogger
import kotlin.math.max
import kotlin.math.min

// 定义一个回调接口
interface EraserCallback {
    fun onMove(x: Float, y: Float)
}

class EraserView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    var eraserWidth: Float = 20f,
) : View(context, attrs, defStyleAttr) {

    var brushWidth = eraserWidth
    var isEraserMode = true

    private lateinit var bitmap: Bitmap
    private lateinit var canvas: Canvas

    private val bitmapPaint = Paint()
    private var canvasBitmap: Bitmap? = null

    var callback: EraserCallback? = null

    private lateinit var bitmapOriginal: Bitmap

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap)

        val imgBitmap = BitmapFactory.decodeResource(resources, R.mipmap.img_2)
        val imgScaledBitmap = Bitmap.createScaledBitmap(imgBitmap, w, h, true)
        canvasBitmap = imgScaledBitmap
        canvas.drawBitmap(imgScaledBitmap, 0f, 0f, bitmapPaint)

        // Create a copy of the original bitmap
        bitmapOriginal = imgScaledBitmap.copy(Bitmap.Config.ARGB_8888, true)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, 0f, 0f, bitmapPaint)
    }

    private var currentPath: Path = Path()
    private val paths = mutableListOf<Pair<Path, Paint>>()
    private val undonePaths = mutableListOf<Pair<Path, Paint>>()


    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath = Path().apply {
                    moveTo(event.x, event.y)
                }

                val currentPaint = Paint().apply {
                    color =
                        if (isEraserMode) android.graphics.Color.TRANSPARENT else bitmap.getPixel(
                            event.x.toInt(),
                            event.y.toInt()
                        )
                    xfermode = if (isEraserMode) PorterDuffXfermode(PorterDuff.Mode.CLEAR) else null
                    style = Paint.Style.STROKE
                    strokeWidth = brushWidth
                    isAntiAlias = true
                }
                paths.add(currentPath to currentPaint)
            }

            MotionEvent.ACTION_MOVE -> {
                currentPath.lineTo(event.x, event.y)

                if (!isEraserMode) {
                    val x = event.x.toInt()
                    val y = event.y.toInt()
                    val radius = brushWidth.toInt()

                    val xBound = max(0, x - radius)
                    val yBound = max(0, y - radius)
                    val widthBound = min(bitmapOriginal.width, x + radius) - xBound
                    val heightBound = min(bitmapOriginal.height, y + radius) - yBound
                    // Get the pixels from the original bitmap
                    try {
                        val pixels = IntArray(widthBound * heightBound)
                        bitmapOriginal.getPixels(
                            pixels,
                            0,
                            widthBound,
                            xBound,
                            yBound,
                            widthBound,
                            heightBound
                        )
                        bitmap.setPixels(
                            pixels,
                            0,
                            widthBound,
                            xBound,
                            yBound,
                            widthBound,
                            heightBound
                        )
                    } catch (e: Exception) {
                        // Handle the exception
                        XLogger.d("算法======error")
                    }
                }
                callback?.onMove(event.x, event.y)
            }

            else -> return false
        }

        drawPaths()
        return true
    }


    fun undo() {
        if (paths.isNotEmpty()) {
            undonePaths.add(paths.removeAt(paths.lastIndex))
            drawPaths()
        }
    }

    fun redo() {
        if (undonePaths.isNotEmpty()) {
            paths.add(undonePaths.removeAt(undonePaths.lastIndex))
            drawPaths()
        }
    }

    private fun drawPaths() {
        val imgBitmap = BitmapFactory.decodeResource(resources, R.mipmap.img_2)
        val imgScaledBitmap = Bitmap.createScaledBitmap(imgBitmap, width, height, true)
        canvas.drawBitmap(imgScaledBitmap, 0f, 0f, bitmapPaint)

        paths.forEach { (path, paint) ->
            canvas.drawPath(path, paint)
        }
        invalidate()

    }
}


@RequiresApi(Build.VERSION_CODES.R)
@ExperimentalComposeUiApi
@Composable
fun ImagePaintPage(navCtrl: NavHostController, title: String) {
    val context = LocalContext.current

    CommonToolbar(navCtrl, title) {
        var eraserWidth by remember { mutableFloatStateOf(20f) }
        var touchPosition by remember { mutableStateOf(Offset.Zero) }
        val eraserView = remember { EraserView(context, eraserWidth = eraserWidth) }

        eraserView.callback = object : EraserCallback {
            override fun onMove(x: Float, y: Float) {
                touchPosition = Offset(x, y)
            }
        }

        Column {
            Slider(
                value = eraserWidth,
                onValueChange = { newWidth ->
                    eraserWidth = newWidth
                },
                valueRange = 1f..100f
            )

            Button(onClick = { eraserView.undo() }) {
                Text("Undo")
            }

            Button(onClick = { eraserView.redo() }) {
                Text("Redo")
            }

            Button(onClick = { eraserView.isEraserMode = !eraserView.isEraserMode }) {
                Text("Switch Mode")
            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
            ) {
                AndroidView(
                    modifier = Modifier
                        .fillMaxSize(),
                    factory = { eraserView },
                    update = { view -> view.eraserWidth = eraserWidth }
                )

                androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = Color.Black,
                        radius = eraserWidth / 2,
                        center = touchPosition,
                        style = Stroke(width = 1.dp.toPx())
                    )
                }
            }
        }
    }
}
