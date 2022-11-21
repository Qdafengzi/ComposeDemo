package com.example.composedemo.adapter

import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.recyclerview.widget.RecyclerView
import com.example.composedemo.data.VideoEntity

class RvGoodsAdapter: RecyclerView.Adapter<MyComposeViewHolder>() {

    var goodsList = mutableListOf<VideoEntity>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MyComposeViewHolder {
        return MyComposeViewHolder(ComposeView(parent.context))
    }

    override fun onBindViewHolder(holder: MyComposeViewHolder, position: Int) {
        holder.bind("${goodsList[position].text}")
    }

    override fun getItemCount(): Int {
        return goodsList.size
    }
}
