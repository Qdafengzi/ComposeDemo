package com.example.composedemo.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FlowDataViewModel @Inject constructor() : ViewModel() {

    private val _fileName = MutableStateFlow("")
    val fileName = _fileName.asStateFlow()


    fun updateData() {
        viewModelScope.launch (Dispatchers.IO){
            (0..99).forEachIndexed { index, i ->
                delay(1)
                _fileName.update {
                    "index $i"
                }
            }
        }

    }
}