package com.example.composedemo.ui.page.home

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PauseCircleFilled
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.compose.PlayerSurface
import androidx.media3.ui.compose.state.rememberPlayPauseButtonState
import androidx.navigation.NavHostController
import com.example.composedemo.ui.widget.CommonToolbar


@UnstableApi
@Composable
fun Media3ComposePlayerPage(
    navCtrl: NavHostController,
    title: String,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val videoUri = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        videoUri.value = uri
    }

    CommonToolbar(navCtrl, title) {
        Button(onClick = {
            launcher.launch("video/*")
        }) {
            Text("pick video")
        }

        if (videoUri.value != null) {
            ExoPlayerComposableBasedOnNewModule(lifecycleOwner.lifecycle, lifecycleOwner,videoUri.value)
        }
    }
}


@UnstableApi
@Composable
fun ExoPlayerComposableBasedOnNewModule(
    lifecycle: Lifecycle,
    lifecycleOwner: LifecycleOwner,
    videoUri: Uri?,
) {
    val context = LocalContext.current
    var showControls by remember { mutableStateOf(true) }

    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .build()
            .also { exoPlayer ->
                val mediaItem = MediaItem.Builder()
                    .setUri(videoUri)
                    .build()
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.prepare()
            }
    }
    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    exoPlayer.pause()
                }

                Lifecycle.Event.ON_RESUME -> {
                    exoPlayer.play()
                }

                else -> {}
            }
        }
        lifecycle.addObserver(observer)
        onDispose {
            exoPlayer.release()
            lifecycle.removeObserver(observer)
        }
    }

    Box(modifier = Modifier.fillMaxWidth().aspectRatio(4/3f)) {
        PlayerSurface(player = exoPlayer, modifier = Modifier)
        if (showControls) {
            PlayPauseButton(exoPlayer, modifier = Modifier.align(Alignment.Center))
        }
    }
}

@UnstableApi
@Composable
fun PlayPauseButton(player: Player, modifier: Modifier = Modifier) {
    val state = rememberPlayPauseButtonState(player)
    val icon = if (state.showPlay) Icons.Default.PlayCircleFilled else Icons.Default.PauseCircleFilled
    val contentDescription =
        if (state.showPlay) "Play" else "PAUSE"
    val graySemiTransparentBG = Color.Gray.copy(alpha = 0.1f)
    val btnModifier =
        modifier
            .size(100.dp)
            .background(graySemiTransparentBG, CircleShape)
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = state::onClick,
            modifier = btnModifier,
            enabled = state.isEnabled,

        ) {
            Icon(icon, contentDescription = contentDescription, tint = Color.White)
        }
    }
}