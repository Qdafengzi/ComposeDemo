package com.example.composedemo.app

import androidx.compose.ui.graphics.Color
import com.example.composedemo.data.VoteEntity
import com.example.composedemo.utils.XLogger
import java.math.RoundingMode
import java.text.DecimalFormat

/**
 * Created by finn on 2022/8/24
 */
object Constant {

    /**
     * 获取4个随机数
     */
    fun getRandomNum(): MutableList<Float> {
        val list = mutableListOf<Int>()
        repeat(4) {
            val randoms = (0..10000).random()
            list.add(randoms)
            XLogger.d("随机数------->$randoms")
        }

        val sum = list.sum()

        val valueList = mutableListOf<Float>()
        list.forEach {
            val percent = it / sum.toFloat()
            val format = DecimalFormat("0.##")
            format.roundingMode = RoundingMode.HALF_UP
            val value = format.format(percent)
            valueList.add(value.toFloat())
        }
        return valueList
    }

    /**
     * 投票的数据bean
     */
    fun getVoteList(): MutableList<VoteEntity> {
        val randomList = getRandomNum()
        val colors = listOf(Color(0xFFDD493E), Color(0xFFCE4372), Color(0xFF4F62CF), Color(0xFF2196F3))
        val voteTexts = listOf("中山大学", "厦门大学", "武汉大学", "四川大学")
        val dataList = mutableListOf<VoteEntity>()
        randomList.forEachIndexed { index, fl ->
            dataList.add(VoteEntity(percent = fl,  color = colors[index], voteText = voteTexts[index]))
        }
        return dataList
    }
}