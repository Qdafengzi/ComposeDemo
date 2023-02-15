package com.example.composedemo.ui.page.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import com.example.composedemo.utils.XLogger

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TransformationPage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        var text by remember {
            mutableStateOf(TextFieldValue())
        }

        val text1 = "Here is my website." // "my website" contains a link

        val link = "https://dmytroshuba.com/"
        val annotatedText = attachLink(
            source = text1, segment = "my website", link = link
        )
        ClickableText(text = annotatedText, onClick = {
            annotatedText.getStringAnnotations(TAG_URL, it, it).firstOrNull()
                ?.let { url -> {
                    XLogger.d("======》打开")
                } }
        })

        TextField(modifier = Modifier.fillMaxWidth(), value = text, onValueChange = {

            text = it

        }, visualTransformation = object : VisualTransformation {
            fun transform(original: String): String {
                val trimmed: String = original.take(8)
                if (trimmed.length < 4) return trimmed
                if (trimmed.length == 4) return "$trimmed-"
                val (year, monthAndOrDate) = trimmed.chunked(4)
                if (trimmed.length == 5) return "$year-$monthAndOrDate"
                if (trimmed.length == 6) return "$year-$monthAndOrDate-"
                val (month, date) = monthAndOrDate.chunked(2)
                return "$year-$month-$date"
            }

            override fun filter(text: AnnotatedString): TransformedText {
                return TransformedText(
                    AnnotatedString(transform(text.text)),
                    object : OffsetMapping {
                        override fun originalToTransformed(offset: Int): Int {
                            if (offset <= 3) return offset
                            if (offset <= 5) return offset + 1
                            if (offset <= 7) return offset + 2
                            return 10
                        }

                        override fun transformedToOriginal(offset: Int): Int {
                            if (offset <= 4) return offset
                            if (offset <= 7) return offset - 1
                            if (offset <= 10) return offset - 2
                            return 8
                        }
                    })
            }
        })
    }
}

private const val TAG_URL = "ANNOTATION_TAG_URL"

fun attachLink(
    source: String, segment: String, link: String
): AnnotatedString {
    val builder = AnnotatedString.Builder() // builder to attach metadata(link)
    builder.append(source) // load current text into the builder

    val start = source.indexOf(segment) // get the start of the span "my website"
    val end = start + segment.length // get the end of the span
    val hyperlinkStyle = SpanStyle(
        color = Color.Blue, textDecoration = TextDecoration.Underline
    ) // create a hyperlink text style

    builder.addStyle(hyperlinkStyle, start, end) // style "my website" to make it look like a link
    builder.addStringAnnotation(
        TAG_URL, link, start, end
    ) // attach the link to the span. We can then access it via the TAG_URL

    return builder.toAnnotatedString()
}