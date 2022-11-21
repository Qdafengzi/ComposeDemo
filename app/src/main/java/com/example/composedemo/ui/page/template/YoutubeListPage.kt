package com.example.composedemo.ui.page.template

import android.view.LayoutInflater
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavHostController
import com.example.composedemo.R
import com.example.composedemo.databinding.ActivityYoutubeListItemBinding
import com.example.composedemo.ui.widget.CommonToolbar
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener

@Composable
fun YoutubeListPage(navCtrl: NavHostController, title: String) {

    val videoIds = arrayOf(
        "6JYIGclVQdw",
        "LvetJ9U_tVY",
        "6JYIGclVQdw",
        "LvetJ9U_tVY",
        "6JYIGclVQdw",
        "LvetJ9U_tVY",
        "6JYIGclVQdw",
        "LvetJ9U_tVY",
        "6JYIGclVQdw",
        "LvetJ9U_tVY",
        "6JYIGclVQdw",
        "LvetJ9U_tVY"
    )
    CommonToolbar(navCtrl, title) {
        LazyColumn(modifier = Modifier.fillMaxSize(), content = {
            videoIds.forEachIndexed { index, s ->
                item {
                    AndroidView(modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(4 / 3f), factory = { context ->
                        val binding = DataBindingUtil.inflate<ActivityYoutubeListItemBinding>(
                            LayoutInflater.from(context),
                            R.layout.activity_youtube_list_item,
                            null,
                            false
                        )

                        var player: YouTubePlayer
                        binding.youtubePlayerView.addYouTubePlayerListener(object :
                            YouTubePlayerListener {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                player = youTubePlayer
                                player.cueVideo(s, 0f)
                            }

                            override fun onApiChange(youTubePlayer: YouTubePlayer) {

                            }

                            override fun onCurrentSecond(
                                youTubePlayer: YouTubePlayer,
                                second: Float
                            ) {
                            }

                            override fun onError(
                                youTubePlayer: YouTubePlayer,
                                error: PlayerConstants.PlayerError
                            ) {
                            }

                            override fun onPlaybackQualityChange(
                                youTubePlayer: YouTubePlayer,
                                playbackQuality: PlayerConstants.PlaybackQuality
                            ) {
                            }

                            override fun onPlaybackRateChange(
                                youTubePlayer: YouTubePlayer,
                                playbackRate: PlayerConstants.PlaybackRate
                            ) {
                            }


                            override fun onStateChange(
                                youTubePlayer: YouTubePlayer,
                                state: PlayerConstants.PlayerState
                            ) {
                            }

                            override fun onVideoDuration(
                                youTubePlayer: YouTubePlayer,
                                duration: Float
                            ) {
                            }

                            override fun onVideoId(
                                youTubePlayer: YouTubePlayer,
                                videoId: String
                            ) {

                            }

                            override fun onVideoLoadedFraction(
                                youTubePlayer: YouTubePlayer,
                                loadedFraction: Float
                            ) {
                            }
                        })
                        binding.root
                    })
                }
            }
        })
    }
}