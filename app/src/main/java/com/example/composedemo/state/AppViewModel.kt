package com.example.composedemo.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.composedemo.data.StateBean
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {

    val mStateOne: MutableSharedFlow<StateBean> = MutableSharedFlow()
    val mStateTwo: MutableStateFlow<StateBean> = MutableStateFlow(StateBean())

    fun setStateOne(str: String) {
        viewModelScope.launch {

            if (mStateOne.asLiveData().value == null) {
                mStateOne.emit(StateBean(str))
            } else {
                mStateOne.asLiveData().value?.content = str
                mStateOne.emit(mStateOne.asLiveData().value!!)
            }
        }
    }

    fun setStateTwo(str: String) {
        viewModelScope.launch {
            if (mStateTwo.asLiveData().value == null) {
                mStateTwo.emit(StateBean(str))
            } else {
                mStateTwo.asLiveData().value?.content = str
                mStateTwo.emit(mStateTwo.asLiveData().value!!)
            }
        }
    }


}