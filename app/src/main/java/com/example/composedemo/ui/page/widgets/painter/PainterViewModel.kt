package com.example.composedemo.ui.page.widgets.painter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


data class DrawPath(
    val pathList :List<Pair<Path, Paint>> = listOf(),
    val undonePathList :List<Pair<Path, Paint>> = listOf(),
)

@HiltViewModel
class PainterViewModel @Inject constructor() : ViewModel() {

    var eraserWidth by mutableFloatStateOf(20f)

    var isEraseModel by mutableStateOf(false)


    private val _data:MutableStateFlow<DrawPath> = MutableStateFlow(DrawPath())
    val data = _data.asStateFlow()

//    val pathList = mutableStateListOf<Pair<Path, Paint>>()

    val undonePaths = mutableStateListOf<Pair<Path, Paint>>()

    fun updatePathList(data:Pair<Path, Paint>){
        val list = _data.value.pathList.toMutableList()
        list.add(data)
        _data.update {
            it.copy(pathList = list)
        }
    }

    fun undo() {
        if (_data.value.pathList.isNotEmpty()){
            val list = _data.value.undonePathList.toMutableList()
            val pathList =   _data.value.pathList.toMutableList()
            val data =pathList.removeLast()

            list.add(data)

            _data.update {
                it.copy(pathList = pathList,undonePathList= list)
            }
        }
    }

    fun redo() {
        if (_data.value.undonePathList.isNotEmpty()) {
            val list = _data.value.pathList.toMutableList()
            val undonePathList = _data.value.undonePathList.toMutableList()
            val data = undonePathList.removeLast()

            list.add(data)

            _data.update {
                it.copy(pathList = list, undonePathList = undonePathList)
            }
        }
    }


}