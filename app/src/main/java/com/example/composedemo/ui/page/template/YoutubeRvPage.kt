package com.example.composedemo.ui.page.template

import android.view.LayoutInflater
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavHostController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.composedemo.R
import com.example.composedemo.adapter.YoutubeListAdapter
import com.example.composedemo.data.VideoEntity
import com.example.composedemo.databinding.ActivityYoutubeListRvBinding
import com.example.composedemo.ui.widget.CommonToolbar
import com.example.composedemo.utils.XLogger

@Composable
fun YoutubeRvPage(navCtrl: NavHostController, title: String) {

    val videoIds = mutableListOf(
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

    val list = mutableListOf<VideoEntity>()
    repeat(30) {
        if (it % 3 == 0) {
            list.add(VideoEntity(videoId = videoIds.removeFirst()))
        } else {
            list.add(VideoEntity(text = "item is $it"))
        }
    }


    CommonToolbar(navCtrl, title) {
        val lifecycleOwner = LocalLifecycleOwner.current
        XLogger.d("---------->CommonToolbar")
        AndroidView(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 12.dp)
                .fillMaxWidth()
                .background(color = Color.White),
            factory = { context ->
                val binding = DataBindingUtil.inflate<ActivityYoutubeListRvBinding>(
                    LayoutInflater.from(context),
                    R.layout.activity_youtube_list_rv,
                    null,
                    false
                )
                binding.rv.setHasFixedSize(true)

                val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
                binding.rv.layoutManager = mLayoutManager

                val youtubeListAdapter = YoutubeListAdapter(list, lifecycleOwner.lifecycle)
                binding.rv.adapter = youtubeListAdapter

                binding.root
            })
    }
}
