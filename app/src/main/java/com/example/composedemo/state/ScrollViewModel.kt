package com.example.composedemo.state

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

/**
 * Created by finn on 2022/4/14
 */
class ScrollViewModel:ViewModel() {
    val list: MutableList<String> = mutableStateListOf()
    init {
        repeat(100){

        list.add("哈哈哈哈哈")
        list.add("张三购买彩票中了20W")
        list.add("张三购买彩票中了20W")
        list.add("张三购买彩票中了20W")
        list.add("187****0405购买彩票中了看见了大事")
        }

    }

}