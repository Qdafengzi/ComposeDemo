package com.example.composedemo.state

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

/**
 * Created by finn on 2022/4/14
 */
class ScrollViewModel : ViewModel() {
    val list: MutableList<String> = mutableStateListOf()
    init {
        repeat(50) {
            list.add("item  $it")
        }
    }

}