package com.example.composedemo.ui.page.template

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import com.example.composedemo.utils.XLogger
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabel
import com.google.mlkit.vision.label.ImageLabeler
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.google.mlkit.vision.objects.DetectedObject
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.chinese.ChineseTextRecognizerOptions
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


@Composable
fun MLKitPage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
//        MLKitSample()
        MLKitIdCardSample()
    }
}

@Composable
fun MLKitIdCardSample() {

    var text by remember {
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()
    Column(Modifier.fillMaxSize()) {
        val detctedObject = remember { mutableStateListOf<DetectedObject>() }
        // When using Chinese script library
        val recognizer = TextRecognition.getClient(ChineseTextRecognizerOptions.Builder().build())

        //Load Image
        val context = LocalContext.current
        val bmp = remember(context) {
            context.assetsToBitmap("id_card.png")!!
//            context.assetsToBitmap("car_num.jpeg")!!
        }

        val image = InputImage.fromBitmap(bmp, 0)


        Image(bitmap = bmp.asImageBitmap(), contentDescription = "")


        val result1 = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                // Task completed successfully
                //                    L
                for (block in visionText.textBlocks) {
                    val blockText = block.text
                    val blockCornerPoints = block.cornerPoints
                    val blockFrame = block.boundingBox
//                    XLogger.d("blockText========>$blockText")
                    for (line in block.lines) {
                        val lineText = line.text
                        val lineCornerPoints = line.cornerPoints
                        val lineFrame = line.boundingBox
                        for (element in line.elements) {
                            val elementText = element.text
                            val elementCornerPoints = element.cornerPoints
                            val elementFrame = element.boundingBox
                            XLogger.d("elementText========>$elementText")
                            if(elementText.contains("身份号码")){
                                text = elementText
                            }
                        }
                    }
                }
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                // ...
            }

        Text(text = text, color = Color.Black, fontSize = 20.sp)

    }
}
@Composable
fun MLKitSample() {

    var text by remember {
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()
    Column(Modifier.fillMaxSize()) {
        val detctedObject = remember { mutableStateListOf<DetectedObject>() }
        // When using Chinese script library
        val recognizer = TextRecognition.getClient(ChineseTextRecognizerOptions.Builder().build())

        //Load Image
        val context = LocalContext.current
        val bmp = remember(context) {
            context.assetsToBitmap("chinese_text.png")!!
        }

        val image = InputImage.fromBitmap(bmp, 0)


        Image(bitmap = bmp.asImageBitmap(), contentDescription = "")


        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                // Task completed successfully
                //                    L
                text = visionText.text
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                // ...
            }

        Text(text = text, color = Color.Black, fontSize = 20.sp)

    }
}

@Composable
fun MLKitSample3() {

    var text by remember {
        mutableStateOf("")
    }
    val coroutineScope = rememberCoroutineScope()
    Column(Modifier.fillMaxSize()) {
        val detctedObject = remember { mutableStateListOf<DetectedObject>() }
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        //Load Image
        val context = LocalContext.current
        val bmp = remember(context) {
            context.assetsToBitmap("text1.png")!!
        }

        val image = InputImage.fromBitmap(bmp, 0)


        Image(bitmap = bmp.asImageBitmap(), contentDescription = "")


        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                // Task completed successfully
                //                    L
                text = visionText.text
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                // ...
            }

        Text(text = text, color = Color.Black, fontSize = 20.sp)

    }
}

@Composable
fun MLKitSample2() {
    val coroutineScope = rememberCoroutineScope()
    Column(Modifier.fillMaxSize()) {

        val detctedObject = remember { mutableStateListOf<DetectedObject>() }

        //Load Image
        val context = LocalContext.current
        val bmp = remember(context) {
            context.assetsToBitmap("cat.jpeg")!!
        }

        androidx.compose.foundation.Canvas(
            Modifier.aspectRatio(
                bmp.width.toFloat() / bmp.height.toFloat()
            )
        ) {

            drawIntoCanvas { canvas ->
                canvas.withSave {
                    canvas.scale(size.width / bmp.width)
                    canvas.drawImage( // 绘制 image
                        image = bmp.asImageBitmap(), Offset(0f, 0f), Paint()
                    )
                    detctedObject.forEach {
                        canvas.drawRect( //绘制目标检测的边框
                            it.boundingBox.toComposeRect(),
                            Paint().apply {
                                color = Color.Red.copy(alpha = 0.5f)
                                style = PaintingStyle.Stroke
                                strokeWidth = bmp.width * 0.01f
                            })
                        if (it.labels.isNotEmpty()) {
                            canvas.nativeCanvas.drawText( //绘制物体识别信息
                                it.labels.first().text,
                                it.boundingBox.left.toFloat(),
                                it.boundingBox.top.toFloat(),
                                android.graphics.Paint().apply {
                                    color = Color.Green.toArgb()
                                    textSize = bmp.width * 0.05f
                                })
                        }

                    }
                }
            }
        }

        Button(
            onClick = {
                val options =
                    ObjectDetectorOptions.Builder()
                        .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
                        .enableMultipleObjects()
                        .enableClassification()
                        .build()

                val objectDetector = ObjectDetection.getClient(options)
                val image = InputImage.fromBitmap(bmp, 0)

                objectDetector.process(image)
                    .addOnSuccessListener { detectedObjects ->
                        // Task completed successfully
                        coroutineScope.launch {
                            detctedObject.clear()
                            detctedObject.addAll(getLabels(bmp, detectedObjects).toList())
                        }
                    }
                    .addOnFailureListener { e ->
                        // Task failed with an exception
                        // ...
                    }

            })
        { Text("Object Detect") }

    }
}

private fun getLabels(
    bitmap: Bitmap,
    objects: List<DetectedObject>
) = flow {
    val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)
    for (obj in objects) {
        val bounds = obj.boundingBox
        val croppedBitmap = Bitmap.createBitmap(
            bitmap,
            bounds.left,
            bounds.top,
            bounds.width(),
            bounds.height()
        )

        emit(
            DetectedObject(
                obj.boundingBox,
                obj.trackingId,
                getLabel(labeler, croppedBitmap).map {
                    //转换为 DetectedObject.Label
                    DetectedObject.Label(it.text, it.confidence, it.index)
                })
        )
    }
}

suspend fun getLabel(labeler: ImageLabeler, image: Bitmap): List<ImageLabel> =
    suspendCancellableCoroutine { cont ->
        labeler.process(InputImage.fromBitmap(image, 0))
            .addOnSuccessListener { labels ->
                // Task completed successfully
                cont.resume(labels)
            }
    }


@Composable
fun MLKitSample1() {

    Column {
        var imageLabel by remember { mutableStateOf("") }

        //Load Image
        val context = LocalContext.current
        val bmp = remember(context) {
//                context.assetsToBitmap("cat.jpeg")!!
            context.assetsToBitmap("person.jpeg")!!
        }

        Image(bitmap = bmp.asImageBitmap(), contentDescription = "")

        val coroutineScope = rememberCoroutineScope()

        Button(
            onClick = {
                //TODO : 图像识别具体逻辑，见后文

                val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)
                val image = InputImage.fromBitmap(bmp, 0)
                labeler.process(image).addOnSuccessListener { labels: List<ImageLabel> ->
                    // Task completed successfully
                    imageLabel = labels.scan("") { acc, label ->
                        acc + "${label.text} : ${label.confidence}\n"
                    }.last()
                }.addOnFailureListener {
                    // Task failed with an exception
                }


            })
        { Text("Image Labeling") }

        Text(imageLabel, Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
    }
}

fun Context.assetsToBitmap(fileName: String): Bitmap? =
    assets.open(fileName).use {
        BitmapFactory.decodeStream(it)
    }