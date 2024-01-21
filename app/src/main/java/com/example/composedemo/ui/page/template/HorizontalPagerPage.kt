package com.example.composedemo.ui.page.template

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavHostController
import com.example.composedemo.R
import com.example.composedemo.app.App.Companion.mSWebView
import com.example.composedemo.ui.widget.CommonToolbar
import com.example.composedemo.utils.XLogger
import kotlinx.coroutines.launch

/**
 * WebView预热
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerPage(navCtrl: NavHostController, title: String) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val loginTypeArray = listOf("A","B","C")
    val pagerState = rememberPagerState(pageCount = {
        loginTypeArray.size
    })

    val coroutineScope = rememberCoroutineScope()

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(key1 = selectedIndex, block = {
        coroutineScope.launch {
            pagerState.scrollToPage(selectedIndex)
        }
    })

    CommonToolbar(navCtrl, title) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 58.dp),
            horizontalArrangement = Arrangement.End
        ) {
            loginTypeArray.forEachIndexed { index, s ->
                Text(
                    text = s,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFD5EEFA),
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .wrapContentWidth()
                        .background(
                            // #145F83  #0082C0
                            color = if (selectedIndex == index) Color(0xFF0082C0) else Color(
                                0xFF145F83
                            ),
                            shape =
                            RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                        )
                        .clickable() {
                            selectedIndex = index
                            when (index) {
                                0 -> {
                                    keyboardController?.hide()
                                    XLogger.d("点击头像开始111111")
                                }

                                1 -> {
                                    keyboardController?.hide()
                                }

                                2 -> {
                                    keyboardController?.show()
                                }

                                3 -> {
                                }
                            }
                        }
                        .padding(horizontal = 27.dp, vertical = 19.dp)
                )
            }
        }




        val stroke = Stroke(
            width = 2f,
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(6f, 6f), 0f)
        )
        HorizontalPager(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
                .aspectRatio(183 / 136f)
                .background(color = Color(0xFF0082C0), shape = RoundedCornerShape(20.dp))
                .drawBehind {
                    drawRoundRect(
                        color = Color(0xFFB5E6FC),
                        style = stroke,
                        cornerRadius = CornerRadius(20f),
                        topLeft = Offset(2f, 2f),
                        size = size.copy(width = size.width - 4, height = size.height - 4)
                    )
                },
            userScrollEnabled = false,
            state = pagerState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                when (it) {
                    0 -> {
                        Box(modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Red))
                    }

                    1 -> {
                      Box(modifier = Modifier
                          .fillMaxSize()
                          .background(color = Color.Green))
                    }

                    2 -> {
                        PhoneLoginView()
                    }
                }
            }
        }


    }
}


@Composable
fun PhoneLoginView() {
    val keyboardController = LocalSoftwareKeyboardController.current

//    val localFocusManager =  LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME->{
                    focusRequester.requestFocus()
                }
                Lifecycle.Event.ON_START->{
                    XLogger.d("ON_START")
                }
                Lifecycle.Event.ON_CREATE -> {
                    XLogger.d("ON_CREATE")
                }

                Lifecycle.Event.ON_STOP -> {

                }

                else -> {}
            }
        }
        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)
        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Row(
        modifier = Modifier
            .padding(top = 155.dp)
            .wrapContentWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.mipmap.coil_icon), contentDescription = null,
            modifier = Modifier.size(71.dp)
        )

        var phone by remember {
            mutableStateOf("")
        }

        TextField(
            modifier = Modifier
                .padding(start = 24.dp)
                .focusRequester(focusRequester),
            value = phone,
            onValueChange = {
                if (it.length > 11) {
                  XLogger.d("手机号输入不正确")
                    return@TextField
                }
                phone = it
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFC8ECFC),
                textColor = Color.Black,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            placeholder = {
                Text(text = "请输入手机号", color = Color.DarkGray, fontSize = 28.sp)
            },
            textStyle = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium,
            ),
            shape = RoundedCornerShape(2.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                },
            ),
            singleLine = true,
        )

        Image(
            painter = painterResource(id = R.mipmap.coil_icon),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 18.dp)
                .size(134.dp, 64.dp)
                .clickable {
                    keyboardController?.hide()
                }
        )
    }

    val buildAnnotatedString = buildAnnotatedString {
        withStyle(
            SpanStyle(
                color = Color.White
            )
        ) {
            append("请输入会员注册")
        }

        withStyle(
            SpanStyle(
                color = Color(0xFFF3C314)
            )
        ) {
            append("手机号码")
        }

        withStyle(
            SpanStyle(
                color = Color.White
            )
        ) {
            append("，点击")
        }

        withStyle(
            SpanStyle(
                color = Color(0xFFF3C314)
            )
        ) {
            append("查询")
        }

//        withStyle(
//            SpanStyle(
//                color = Color.White
//            )
//        ) {
//            append("后")
//        }
//
//        withStyle(
//            SpanStyle(
//                color = Color(0xFFF3C314)
//            )
//        ) {
//            append("确定")
//        }
    }

    Text(
        modifier = Modifier.padding(top = 119.dp, bottom = 100.dp),
        text = buildAnnotatedString,
        fontWeight = FontWeight.SemiBold,
        fontSize = 34.sp
    )
}