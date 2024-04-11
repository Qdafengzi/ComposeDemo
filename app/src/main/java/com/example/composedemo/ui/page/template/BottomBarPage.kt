package com.example.composedemo.ui.page.template

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomBarPage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        BottomBarContent()
    }
}


@Preview
@Composable
fun BottomBarContentPreview() {
    BottomBarContent()
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomBarContent() {
    val bottomSheetState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        BottomSheetScaffold(
            backgroundColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .fillMaxSize(),
            scaffoldState = bottomSheetState,
            topBar = {

            },
            sheetContent = {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.4f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Sheet", color = Color.White)
                }
            },
            sheetPeekHeight = 50.dp,
            sheetElevation = 10.dp,
            sheetGesturesEnabled = true,
            sheetBackgroundColor = MaterialTheme.colorScheme.primary,
            sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            sheetContentColor = MaterialTheme.colorScheme.secondary,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 50.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Content111")
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.BottomCenter)
                .background(color = MaterialTheme.colorScheme.inverseSurface),
            contentAlignment = Alignment.Center
        ) {

            if (bottomSheetState.bottomSheetState.isExpanded) {
                Icon(
                    modifier = Modifier
                        .animateContentSize()
                        .clickable {
                            scope.launch {
                                bottomSheetState.bottomSheetState.collapse()
                            }
                        },
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    tint = Color.White,

                )
            } else {
                Text(text = "向上滑动弹出", color = Color.White, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
