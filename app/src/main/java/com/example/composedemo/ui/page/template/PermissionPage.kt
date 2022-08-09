package com.example.composedemo.ui.page.template

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState


@ExperimentalPermissionsApi
@Composable
fun PermissionPage(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        Column() {
            val context = LocalContext.current
            FeatureThatRequiresCameraPermission { startAppSettings(context) }
//            FeatureThatRequiresCameraPermission1 { startAppSettings() }
        }
    }
}


private fun startAppSettings(context: Context) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.data = Uri.parse("package:$context.packageName")
    context.startActivity(intent)
}


@ExperimentalPermissionsApi
@Composable
private fun FeatureThatRequiresCameraPermission(navigateToSettingsScreen: () -> Unit) {
    // Track if the user doesn't want to see the rationale any more.
    var doNotShowRationale by rememberSaveable { mutableStateOf(false) }

    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    when (cameraPermissionState.status) {
        is PermissionStatus.Denied -> {
            Button(onClick = { cameraPermissionState.launchPermissionRequest() }) {
                Text("Ok!")
            }
            Spacer(Modifier.width(8.dp))
        }
        PermissionStatus.Granted -> {
            Text("Camera permission Granted")
        }
    }

}
