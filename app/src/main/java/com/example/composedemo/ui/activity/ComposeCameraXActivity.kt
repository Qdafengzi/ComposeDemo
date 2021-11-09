package com.example.composedemo.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import java.nio.ByteBuffer
import java.util.concurrent.TimeUnit


class ComposeCameraXActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleCameraPreview()
        }
    }

    @Composable
    fun SimpleCameraPreview() {
        val mSelectedColor = remember() {
            mutableStateOf(intArrayOf(0, 0, 0))
        }

        val mColor = remember {
            mutableStateOf(Color.Black)
        }

        val  pointPosition = remember {
            mutableStateOf(Offset(100f, 100f))
        }

        val lifecycleOwner = LocalLifecycleOwner.current
        val context = LocalContext.current
        val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(color = mColor.value)
                .height(40.dp)) {

            }


            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (preview, focusView) = createRefs()


                Box(modifier = Modifier.constrainAs(preview) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }) {
                    AndroidView(
                        factory = { ctx ->
                            val preview = PreviewView(context).apply {
                                this.scaleType = scaleType
                                layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                                // Preview is incorrectly scaled in Compose on some devices without this
                                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                            }

                            val executor = ContextCompat.getMainExecutor(ctx)
                            cameraProviderFuture.addListener({
                                val cameraProvider = cameraProviderFuture.get()

                                val cameraPreView = androidx.camera.core.Preview.Builder().build()
                                val cameraSelector: CameraSelector = CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
                                cameraPreView.setSurfaceProvider(preview.surfaceProvider)

                                val analysis2 = ImageAnalysis.Builder().build()
                                analysis2.setAnalyzer(executor, InfoAnalyzer(mColor, pointPosition, mSelectedColor))

                                val camera = cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, cameraPreView, analysis2)
                                camera.cameraControl.cancelFocusAndMetering()
                            }, executor)
                            preview
                        },
                        modifier = Modifier.fillMaxSize(),
                    )
                }

                Box(modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(focusView) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                    .onSizeChanged {
                        pointPosition.value= Offset((it.width / 2).toFloat(),(it.height / 2).toFloat())
                    }.drawBehind {
                        drawCircle(color = mColor.value,radius = 20f)
                    }) {


                }

            }


        }
    }


    class InfoAnalyzer(var color: MutableState<Color>, val position:MutableState<Offset>, private var mSelectedColor: MutableState<IntArray>) : ImageAnalysis.Analyzer {
        /**
         * Helper extension function used to extract a byte array from an
         * image plane buffer
         */
        private fun ByteBuffer.toByteArray(): ByteArray {
            rewind()    // Rewind the buffer to zero
            val data = ByteArray(remaining())
            get(data)   // Copy the buffer into a byte array
            Log.d("CameraXApp", "ByteArray")
            return data // Return the byte array
        }

        override fun analyze(image: ImageProxy) {
            Log.d("InfoAnalyzer", "imageProxy format is : ${image.format.toString(16)}")
            Log.d("InfoAnalyzer", "imageProxy width is : ${image.width}")
            Log.d("InfoAnalyzer", "imageProxy height is : ${image.height}")
            Log.d("InfoAnalyzer", "imageProxy planes size is : ${image.planes.size}")
            Log.d("InfoAnalyzer", "imageProxy image.timestamp is : ${image.imageInfo.timestamp}")

            Log.d("---------->", "${position.value.x.toInt()}  ${position.value.y.toInt()}")

            val buffer = image.planes[0].buffer
            // Extract image data from callback object
            val data = buffer.toByteArray()
            mSelectedColor.value[0] = 0
            mSelectedColor.value[1] = 0
            mSelectedColor.value[2] = 0
            for (i in 0..5) {
                for (j in 0..5) {
                    addColorFromYUV420(data, mSelectedColor, i * 5 + j + 1, position.value.x.toInt() - 5 + i, position.value.y.toInt() - 5 + j, 100, 500)
                }
            }

            color.value =Color(red = mSelectedColor.value[0],green = mSelectedColor.value[1],blue = mSelectedColor.value[2])
            Log.d("color:", "------->${color.value}")
            image.close()
        }


        private fun addColorFromYUV420(data: ByteArray, averageColor: MutableState<IntArray>, count: Int, x: Int, y: Int, width: Int, height: Int) {
            try {
                val size = width * height
//               val Y: Int = data[y * width + x] and 0xff
                val Y: Int = data[y * width + x].toInt() and 0xff
                val xby2 = x / 2
                val yby2 = y / 2
                val V = (data[size + 2 * xby2 + yby2 * width].toInt() and 0xff) - 128.0f
                val U = (data[size + 2 * xby2 + 1 + yby2 * width].toInt() and 0xff) - 128.0f
                val Yf = 1.164f * Y.toFloat() - 16.0f
                var red = (Yf + 1.596f * V).toInt()
                var green = (Yf - 0.813f * V - 0.391f * U).toInt()
                var blue = (Yf + 2.018f * U).toInt()
                red = if (red < 0) 0 else if (red > 255) 255 else red
                green = if (green < 0) 0 else if (green > 255) 255 else green
                blue = if (blue < 0) 0 else if (blue > 255) 255 else blue
                averageColor.value[0] += (red - averageColor.value[0]) / count
                averageColor.value[1] += (green - averageColor.value[1]) / count
                averageColor.value[2] += (blue - averageColor.value[2]) / count
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }


    inner class LuminosityAnalyzer() : ImageAnalysis.Analyzer {
        private var lastAnalyzedTimestamp = 0L

        /**
         * Helper extension function used to extract a byte array from an
         * image plane buffer
         */
        private fun ByteBuffer.toByteArray(): ByteArray {
            rewind()    // Rewind the buffer to zero
            val data = ByteArray(remaining())
            get(data)   // Copy the buffer into a byte array
            Log.d("CameraXApp", "ByteArray")
            return data // Return the byte array
        }


        override fun analyze(image: ImageProxy) {
            val currentTimestamp = System.currentTimeMillis()
            // Calculate the average luma no more often than every second
            if (currentTimestamp - lastAnalyzedTimestamp >= TimeUnit.SECONDS.toMillis(1)) {
                // Since format in ImageAnalysis is YUV, image.planes[0]
                // contains the Y (luminance) plane
                val buffer = image.planes[0].buffer
                // Extract image data from callback object
                val data = buffer.toByteArray()
                // Convert the data into an array of pixel values
                val pixels = data.map { it.toInt() and 0xFF }
                // Compute average luminance for the image
                val luma = pixels.average()
                // Log the new luma value
                Log.d("CameraXApp", "Average luminosity: $luma")
                image.close()
                // Update timestamp of last analyzed frame
                lastAnalyzedTimestamp = currentTimestamp
            }
            Log.d("CameraXApp", "Average luminosity:")
            image.close()
        }
    }

    private fun bindPreview(lifecycleOwner: LifecycleOwner, previewView: PreviewView, cameraProvider: ProcessCameraProvider) {

//        camera.cameraControl.setZoomRatio(0.5f)
//        camera.cameraInfo.

    }
}