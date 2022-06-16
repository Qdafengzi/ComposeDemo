package com.example.composedemo.ui.page.template

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner
import androidx.loader.app.LoaderManager
import androidx.navigation.NavHostController
import com.example.composedemo.state.LoaderViewModel
import com.example.composedemo.ui.page.widgets.Dimensions
import com.example.composedemo.ui.widget.CommonToolbar
import com.example.composedemo.utils.getActivity

/**
 * Created by finn on 2022/5/24
 */
@Composable
fun SupportScreenSizePage(navCtrl: NavHostController, title: String,viewModel: LoaderViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    CommonToolbar(navCtrl, title) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Card(
                modifier = Modifier
                    .width(Dimensions.width(value = 50f).dp)
                    .height(Dimensions.height(value = 50f).dp),
                backgroundColor = Color.DarkGray,
            ) {
                Text(
                    text = "font size",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
            }
        }
    }

//    viewModel.loadData()
    viewModel.loadFilesData()
}