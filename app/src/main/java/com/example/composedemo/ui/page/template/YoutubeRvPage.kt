package com.example.composedemo.ui.page.template

import android.view.LayoutInflater
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavHostController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.composedemo.R
import com.example.composedemo.adapter.TestAdapter
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
        AndroidView(factory = { context ->
            val binding = DataBindingUtil.inflate<ActivityYoutubeListRvBinding>(
                LayoutInflater.from(context),
                R.layout.activity_youtube_list_rv,
                null,
                false
            )
            binding.rv.setHasFixedSize(true)

            val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
            binding.rv.layoutManager = mLayoutManager


//            val goodsAdapter = RvGoodsAdapter()
//            val goodsList = list.filter {
//                it.videoId.isNullOrBlank()
//            }
//
//            goodsAdapter.goodsList = goodsList as MutableList<VideoEntity>
//
//           val videoList =  list.filter {
//                !it.videoId.isNullOrBlank()
//            }
//
//            val rvVideoAdapter= RvVideoAdapter( lifecycleOwner.lifecycle)
//            rvVideoAdapter.videoList = videoList as MutableList<VideoEntity>
//
//            val adapter = ConcatAdapter(goodsAdapter,rvVideoAdapter)
//            binding.rv.adapter = adapter

            val testAdapter = TestAdapter(list,lifecycleOwner.lifecycle)
            binding.rv.adapter = testAdapter

            binding.root
        })
    }
}
