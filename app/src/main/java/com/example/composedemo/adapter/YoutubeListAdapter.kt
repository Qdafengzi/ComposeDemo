package com.example.composedemo.adapter;

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import com.chad.library.adapter.base.BaseMultiItemAdapter
import com.example.composedemo.R
import com.example.composedemo.data.VideoEntity
import com.example.composedemo.databinding.ActivityYoutubeListItemAdBinding
import com.example.composedemo.databinding.ActivityYoutubeListItemBinding

class YoutubeListAdapter(data: List<VideoEntity>, private val lifecycle: Lifecycle) :
    BaseMultiItemAdapter<VideoEntity>(data) {

    // 在 init 初始化的时候，添加多类型
    init {
        addItemType(ITEM_TYPE_VIDEO,
            object : OnMultiItemAdapterListener<VideoEntity, ItemVideoVH> { // 类型 1
                override fun onCreate(
                    context: Context,
                    parent: ViewGroup,
                    viewType: Int
                ): ItemVideoVH {
                    // 创建 viewholder
                    val viewBinding = DataBindingUtil.inflate<ActivityYoutubeListItemBinding>(
                        LayoutInflater.from(context),
                        R.layout.activity_youtube_list_item,
                        parent,
                        false
                    )
                    lifecycle.addObserver(viewBinding.youtubePlayerView)
                    return ItemVideoVH(viewBinding)
                }

                override fun onBind(holder: ItemVideoVH, position: Int, item: VideoEntity?) {
                    // 绑定 item 数据
                    holder.cueVideo(item?.videoId ?: "")
                }
            }
        )
            .addItemType(ITEM_TYPE_TEXT,
                object : OnMultiItemAdapterListener<VideoEntity, GoodsComposeViewHolder> { // 类型 2
                    override fun onCreate(
                        context: Context, parent: ViewGroup, viewType: Int
                    ): GoodsComposeViewHolder {
                        // 创建 viewholder
                        return GoodsComposeViewHolder(ComposeView(parent.context))
                    }

                    override fun onBind(
                        holder: GoodsComposeViewHolder, position: Int, item: VideoEntity?
                    ) {
                        // 绑定 item 数据
                        item?.let {
                            holder.bind("${item.text}")
                        }
                    }

                    override fun isFullSpanItem(itemType: Int): Boolean {
                        // 使用GridLayoutManager时，此类型的 item 是否是满跨度
                        return true
                    }

                }
            )
            .addItemType(ITEM_TYPE_AD,
                object : OnMultiItemAdapterListener<VideoEntity, ItemAdVH> {
                    override fun onBind(
                        holder: ItemAdVH,
                        position: Int,
                        item: VideoEntity?
                    ) {
                    }

                    override fun onCreate(
                        context: Context,
                        parent: ViewGroup,
                        viewType: Int
                    ): ItemAdVH {
                        val viewBinding = DataBindingUtil.inflate<ActivityYoutubeListItemAdBinding>(
                            LayoutInflater.from(context),
                            R.layout.activity_youtube_list_item_ad,
                            parent,
                            false
                        )
                        return ItemAdVH(viewBinding)
                    }

                })
            .onItemViewType { position, list -> // 根据数据，返回对应的 ItemViewType
                val item = list[position]
                if (item.isAd == true) {
                    ITEM_TYPE_AD
                } else if (!item.text.isNullOrBlank()) {
                    ITEM_TYPE_TEXT
                } else {
                    ITEM_TYPE_VIDEO
                }
            }
    }

    companion object {
        private const val ITEM_TYPE_VIDEO = 0
        private const val ITEM_TYPE_TEXT = 1
        private const val ITEM_TYPE_AD = 2
    }


}

