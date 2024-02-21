package com.example.composedemo.ui.page.template

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.DisplayMetrics
import android.util.Log
import android.util.Rational
import android.util.Size
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.FileOutputOptions
import androidx.camera.video.Recording
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.camera.view.video.AudioConfig
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.composedemo.R
import com.example.composedemo.ui.page.template.camerax.CameraPreview
import com.example.composedemo.ui.page.template.camerax.CameraXViewModel
import com.example.composedemo.ui.page.template.camerax.PhotoBottomSheetContent
import com.example.composedemo.ui.widget.CommonToolbar
import kotlinx.coroutines.launch
import java.io.File


@Composable
fun CameraXPage1(navCtrl: NavHostController, title: String) {
    val context = LocalContext.current
    CommonToolbar(navCtrl, title) {
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CameraXPage(navCtrl: NavHostController, title: String) {

    val lifecycleOwner = LocalLifecycleOwner.current


    val context = LocalContext.current
    CommonToolbar(navCtrl, title) {
        val scope = rememberCoroutineScope()
        val scaffoldState = rememberBottomSheetScaffoldState()
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)


        val controller = remember {
            LifecycleCameraController(context).apply {
                setEnabledUseCases(
                    CameraController.IMAGE_CAPTURE or CameraController.VIDEO_CAPTURE
                )
                cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
//                this.isPinchToZoomEnabled = true
            }
        }

        val configuration = LocalConfiguration.current

        configuration.screenWidthDp

        val viewModel = viewModel<CameraXViewModel>()
        val bitmaps by viewModel.bitmaps.collectAsState()

        BottomSheetScaffold(scaffoldState = scaffoldState, sheetPeekHeight = 0.dp, sheetContent = {
            PhotoBottomSheetContent(
                bitmaps = bitmaps, modifier = Modifier.fillMaxWidth()
            )
        }) { padding ->
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                CameraPreview(
                    controller = controller, modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(color = Color.Magenta)
                )

//                AndroidView(
//                    factory = {
//                       val preview =  PreviewView(it).apply {
//                            this.controller = controller
//                            controller.isPinchToZoomEnabled = true
//                            controller.isTapToFocusEnabled = true
////                            controller.bindToLifecycle(lifecycleOwner)
//                        }
//
//                        cameraProviderFuture.addListener(Runnable {
//                            val cameraProvider = cameraProviderFuture.get()
//                            bindPreview(lifecycleOwner,preview,cameraProvider)
//                        }, ContextCompat.getMainExecutor(context))
//
//                        preview
//
//                    },
//                    modifier = Modifier.fillMaxWidth()
////                        .aspectRatio(4/3f)
//
//                        .background(color = Color.Blue).padding(2.dp)
//                )


                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(color = Color.Blue)) {
                    IconButton(
                        onClick = {
                            controller.cameraSelector =
                                if (controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                                    CameraSelector.DEFAULT_FRONT_CAMERA
                                } else CameraSelector.DEFAULT_BACK_CAMERA
                        }, modifier = Modifier.offset(16.dp, 16.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.mipmap.camer_xchange),
                            contentDescription = "Switch camera",
                            tint = Color.White,
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()

                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        IconButton(onClick = {
                            scope.launch {
                                scaffoldState.bottomSheetState.expand()
                            }
                        }) {
                            Icon(
                                painter = painterResource(R.mipmap.ic_image_take),
                                contentDescription = "Open gallery",
                                tint = Color.White,
                            )
                        }
                        IconButton(onClick = {
                            takePhoto(
                                context = context,
                                controller = controller,
                                onPhotoTaken = viewModel::onTakePhoto
                            )
                        }) {
                            Icon(
                                painter = painterResource(R.mipmap.take_photo),
                                contentDescription = "Take photo",
                                tint = Color.White,
                            )
                        }
                        IconButton(onClick = {
                            recordVideo(context, controller)
                        }) {

                            Icon(
                                painter = painterResource(R.mipmap.ic_record_video),
                                contentDescription = "Record video",
                                tint = Color.White,
                            )
                        }
                    }
                }
            }
        }
    }


    val CAMERAX_PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
    )

    fun hasRequiredPermissions(): Boolean {
        return CAMERAX_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                context, it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }
}

fun bindPreview(
    lifecycleOwner: LifecycleOwner,
    p: PreviewView,
    cameraProvider: ProcessCameraProvider
) {
//    var preview : Preview = Preview.Builder()
//        .build()
    val metrics = DisplayMetrics().also { p.display.getRealMetrics(it) }
    val screenAspectRatio = Rational(metrics.widthPixels, metrics.heightPixels)

    val preview = Preview.Builder()
//        .setTargetResolution(Size(metrics.widthPixels, (metrics.widthPixels * 3) / 4))
//        .setTargetResolution(Size(1000, 1000))

        .setTargetResolution(Size(600, 600))
//        .setDefaultResolution(Size(600,600))
        .setTargetRotation(p.display.rotation)
        .build()

    var cameraSelector: CameraSelector = CameraSelector.Builder()
        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
        .build()

    preview.setSurfaceProvider(p.surfaceProvider)
//    preview.targetRotation = 1/1
    var camera = cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview)
}

fun takePhoto(
    context: Context, controller: LifecycleCameraController, onPhotoTaken: (Bitmap) -> Unit
) {
    //权限
    controller.takePicture(ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                super.onCaptureSuccess(image)

                val matrix = Matrix().apply {
                    postRotate(image.imageInfo.rotationDegrees.toFloat())
                }
                val rotatedBitmap = Bitmap.createBitmap(
                    image.toBitmap(), 0, 0, image.width, image.height, matrix, true
                )

                onPhotoTaken(rotatedBitmap)
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
                Log.e("Camera", "Couldn't take photo: ", exception)
            }
        })
}

@SuppressLint("MissingPermission")
fun recordVideo(context: Context, controller: LifecycleCameraController) {
    var recording: Recording? = null

    if (recording != null) {
        recording?.stop()
        recording = null
        return
    }

//    if(!hasRequiredPermissions()) {
//        return
//    }

    val outputFile = File(context.filesDir, "my-recording.mp4")
    recording = controller.startRecording(
        FileOutputOptions.Builder(outputFile).build(),
        AudioConfig.create(true),
        ContextCompat.getMainExecutor(context),
    ) { event ->
        when (event) {
            is VideoRecordEvent.Finalize -> {
                if (event.hasError()) {
                    recording?.close()
                    recording = null

                    Toast.makeText(
                        context, "Video capture failed", Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        context, "Video capture succeeded", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}