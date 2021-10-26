package com.example.composedemo.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalPermissionsApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = state, enabled = true)
            ) {

                CreateButton(clazz = RowAndColumnActivity::class.java, text = "Row And Column")
                CreateButton(clazz = ModifierActivity::class.java, text = "Modifier")
                CreateButton(clazz = ImageCardActivity::class.java, text = "Card And Image")
                CreateButton(clazz = FontActivity::class.java, text = "Font")
                CreateButton(clazz = StateActivity::class.java, text = "State")
                CreateButton(clazz = SnackBarActivity::class.java, text = "SnackBar")
                CreateButton(clazz = ListActivity::class.java, text = "List")
                CreateButton(clazz = ConstrainLayoutActivity::class.java, text = "ConstrainLayout")
                CreateButton(clazz = ButtonActivity::class.java, text = "Button")
                CreateButton(clazz = WebViewActivity::class.java, text = "WebView")
                CreateButton(clazz = GridListActivity::class.java, text = "Grid")
                CreateButton(clazz = BezierActivity::class.java, text = "Bezier")
                CreateButton(clazz = CanvasActivity::class.java, text = "Canvas")
                CreateButton(clazz = ComplexActivity::class.java, text = "Complex layout")
                CreateButton(clazz = SmartRefreshActivity::class.java, text = "Refresh")
                CreateButton(clazz = XmlAndComposeActivity::class.java, text = "Xml and Compose")
                CreateButton(clazz = ComposeAndXmlActivity::class.java, text = "Compose and Xml")
                CreateButton(clazz = WechatFriendsCircleActivity::class.java, text = "Wechat Friends Circle")
                CreateButton(clazz = ComposeCameraXActivity::class.java, text = "Camerax")
                CreateButton(clazz = PermissionActivity::class.java, text = "Permission")
                CreateButton(clazz = MainActivityNewActivity::class.java, text = "Navigation")
                CreateButton(clazz = BottomNavigationActivity::class.java, text = "Bottom Navigation")
                CreateButton(clazz = ClockActivity::class.java, text = "Clock")
                CreateButton(clazz = NewUiStyleActivity::class.java, text = "New Ui Style")
            }
        }
    }

    @Composable
    fun <T> CreateButton(clazz: Class<T>, text: String, modifier: Modifier = Modifier) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 5.dp, bottom = 5.dp, start = 16.dp, end = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(modifier = modifier
                .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
                onClick = {
                    intent = Intent(this@MainActivity, clazz)
                    startActivity(intent)
                }) {
                Text(text = text, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        }
    }
}
