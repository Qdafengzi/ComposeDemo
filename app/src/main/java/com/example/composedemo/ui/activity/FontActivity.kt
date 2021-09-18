package com.example.composedemo.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedemo.R
import com.example.composedemo.ui.theme.Co_333333

class FontActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //创建字体
            val fontFamily = FontFamily(
                Font(R.font.opensans_bold, FontWeight.Bold),
                Font(R.font.opensans_light, FontWeight.Thin),
                Font(R.font.opensans_semibold, FontWeight.Normal),
            )

            val state = rememberScrollState()
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
                    .verticalScroll(state = state, enabled = true)
            ) {
                Text(text = "Android Jetpack Compose", color = Co_333333)
                Text(stringResource(R.string.app_name), color = Co_333333)
                //可选文字
                SelectionContainer {
                    Text("This text is selectable")
                }

                /**
                 * 666 可以监听到 第几个字符的点击事件
                 */
                ClickableText(
                    text = AnnotatedString("Click Me"),
                    style = TextStyle(fontSize = 35.sp),
                    onClick = { offset ->
                        Log.d("ClickableText", "$offset -th character is clicked.")
                    }
                )

                val annotatedText = buildAnnotatedString {
                    append("Click ")
                    // We attach this *URL* annotation to the following content
                    // until `pop()` is called
                    pushStringAnnotation(
                        tag = "URL",
                        annotation = "https://developer.android.com"
                    )
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("here")
                    }
                    pop()
                }

                ClickableText(
                    text = annotatedText,
                    style = TextStyle(fontSize = 35.sp),
                    onClick = { offset ->
                        // We check if there is an *URL* annotation attached to the text
                        // at the clicked position
                        annotatedText.getStringAnnotations(
                            tag = "URL", start = offset,
                            end = offset
                        )
                            .firstOrNull()?.let { annotation ->
                                // If yes, we log its value
                                Log.d("Clicked URL", annotation.item)
                            }
                    }
                )



                Text(
                    "Android Jetpack Compose Android Jetpack Compose Android Jetpack Compose Android Jetpack Compose",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Thin,
                    fontStyle = FontStyle.Italic,
                    fontFamily = fontFamily,
                    textAlign = TextAlign.Center,
                    letterSpacing = TextUnit.Unspecified,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    "Android Jetpack Compose Android Jetpack Compose Android Jetpack Compose Android Jetpack Compose",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic,
                    fontFamily = fontFamily,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    softWrap = false,
                    maxLines = 1
                )

                Text(
                    "Android Jetpack Compose Android Jetpack Compose Android Jetpack Compose Android Jetpack Compose",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontFamily = fontFamily,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textDecoration = TextDecoration.LineThrough
                )
                Text(
                    "Android Jetpack Compose Android Jetpack Compose Android Jetpack Compose Android Jetpack Compose",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontFamily = fontFamily,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textDecoration = TextDecoration.Underline
                )

                Text(
                    "Android Jetpack Compose Android Jetpack Compose Android Jetpack Compose Android Jetpack Compose",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontFamily = fontFamily,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textDecoration = TextDecoration.None
                )
                Text(
                    text = "Android Jetpack Compose",
                    color = Co_333333,
                    fontSize = 18.sp,
                )

                Text(
//                    text = "Android Jetpack Compose",
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Red,
                                fontSize = 30.sp
                            )
                        ) {
                            append("A")
                        }
                        append("ndroid ")
                        withStyle(
                            style = SpanStyle(
                                color = Color.Red,
                                fontSize = 30.sp
                            )
                        ) {
                            append("J")
                        }
                        append("etpack ")
                        withStyle(
                            style = SpanStyle(
                                color = Color.Red,
                                fontSize = 30.sp
                            )
                        ) {
                            append("C")
                        }
                        append("ompose")

                    },
                    color = Co_333333,
                    fontSize = 20.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
//                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Center,
//                    textDecoration = TextDecoration.LineThrough,
//                    textDecoration = TextDecoration.Underline,
                )


                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Normal,
                                color = Color.Black,
                                fontSize = 30.sp
                            )
                        ) {
                            append("30,000")
                        }
                        append("￥")
                    },
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Center,
                )

                var text by remember { mutableStateOf("") }

                OutlinedTextField(
                    //一下两个同时设置才可以起作用
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    value = text,
                    textStyle = LocalTextStyle.current,
                    onValueChange = {
                        text = it
                    },
                    label = { Text("请输入密码") }
//                    keyboardActions = KeyboardActions()
                )

                Spacer(modifier = Modifier.padding(10.dp))

                var content by remember { mutableStateOf("Compose") }

                TextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("Label") }
                )
            }
        }
    }
}