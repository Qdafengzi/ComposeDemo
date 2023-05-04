package com.example.composedemo.ui.page.template.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import javax.inject.Inject


@HiltViewModel
class LockViewModel @Inject constructor() : ViewModel() {
    private var started: Boolean = false
    var num = mutableStateOf(100 * 1000)
    private val lock = Mutex()


    fun start() {
        viewModelScope.launch(Dispatchers.IO) {
            if (lock.isLocked) {
                lock.unlock()
            }
            if (!started) {
                started = true
                while (true) {
                    if (!lock.isLocked) {
                        delay(100)
                        viewModelScope.launch(Dispatchers.Main) {
                            num.value = num.value - 1
                        }
                    }
                }
            }
        }
    }

    fun stop() {
        viewModelScope.launch(Dispatchers.IO) {
            if (!lock.isLocked) {
                lock.lock()
            }
        }
    }
}