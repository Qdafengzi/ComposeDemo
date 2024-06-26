package com.example.composedemo.ui.page.widgets

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowInsets
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.doOnLayout
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import com.example.composedemo.utils.XLogger


@RequiresApi(Build.VERSION_CODES.R)
@ExperimentalComposeUiApi
@Composable
fun KeyboardPage(navCtrl: NavHostController, title: String) {
    val context = LocalContext.current
    WindowCompat.setDecorFitsSystemWindows((context as Activity).window, false)

    var keyboardStatus by remember {
        mutableStateOf(false)
    }

    (context as Activity).window.decorView.addKeyboardInsetListener {
        keyboardStatus = it
    }

    CommonToolbar(navCtrl, title) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .imePadding()
        ) {
            val text = remember {
                mutableStateOf(TextFieldValue())
            }

            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = text.value,
                onValueChange = { text.value = it },
                placeholder = {
                    Text(text = "请输入内容", color = Color.LightGray)
                }

            )

            var keyBoardShow by remember {
                mutableStateOf(false)
            }

            val isVisible = androidx.compose.foundation.layout.WindowInsets.ime.getBottom(LocalDensity.current) > 0
            LaunchedEffect(key1 = isVisible) {
                keyBoardShow = if (isVisible) {
                    //hide fab button
                    XLogger.d("-------->展示")
                    true
                } else {
                    //show fab button
                    XLogger.d("-------->隐藏")
                    false
                }
            }

            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
                text = "键盘的状态:${if (keyBoardShow) "弹出" else "隐藏"}",
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium)

            Spacer(Modifier.weight(1f))
            if (keyboardStatus) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.DarkGray)
                            .padding(top = 10.dp, bottom = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "😀",
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    text.value =
                                        text.value.copy(
                                            text = text.value.text + "😀",
                                            selection = TextRange((text.value.text + "😀").length)
                                        )
                                },
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "🤣",
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    text.value =
                                        text.value.copy(
                                            text = text.value.text + "🤣",
                                            selection = TextRange((text.value.text + "🤣").length)
                                        )
                                },
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "😇",
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    text.value =
                                        text.value.copy(
                                            text = text.value.text + "😇",
                                            selection = TextRange((text.value.text + "😇").length)
                                        )
                                },
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "🐺",
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    text.value =
                                        text.value.copy(
                                            text = text.value.text + "🐺",
                                            selection = TextRange((text.value.text + "🐺").length)
                                        )
                                },
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "🐽",
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    text.value =
                                        text.value.copy(
                                            text = text.value.text + "🐽",
                                            selection = TextRange((text.value.text + "🐽").length)
                                        )
                                },
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "🐸",
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    text.value = text.value.copy(
                                        text = text.value.text + "🐸",
                                        selection = TextRange((text.value.text + "🐸").length),
                                    )
                                },
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.R)
fun View.addKeyboardInsetListener(keyboardCallback: (visible: Boolean) -> Unit) {
    doOnLayout {
        //get init state of keyboard
        var keyboardVisible = rootWindowInsets?.isVisible(WindowInsets.Type.ime()) == true

        //callback as soon as the layout is set with whether the keyboard is open or not
        keyboardCallback(keyboardVisible)

        //whenever there is an inset change on the App, check if the keyboard is visible.
        setOnApplyWindowInsetsListener { _, windowInsets ->
            val keyboardUpdateCheck =
                rootWindowInsets?.isVisible(WindowInsets.Type.ime()) == true
            //since the observer is hit quite often, only callback when there is a change.
            if (keyboardUpdateCheck != keyboardVisible) {
                keyboardCallback(keyboardUpdateCheck)
                keyboardVisible = keyboardUpdateCheck
            }

            windowInsets
        }
    }
}