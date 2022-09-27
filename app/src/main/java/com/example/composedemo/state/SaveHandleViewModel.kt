package com.example.composedemo.state

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SaveHandleViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {


//    @OptIn(SavedStateHandleSaveableApi::class)
//    val color: Long by savedStateHandle.saveable {
//        mutableStateOf(0xFFFF0000)
//    }

    var color = savedStateHandle.getStateFlow("color", 0xFFFF0000)


    fun changeColor() {
        val newColor =(0..0xFFFFFFFF).random()

        savedStateHandle["color"] = newColor
    }
}