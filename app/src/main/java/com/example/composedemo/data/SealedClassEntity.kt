package com.example.composedemo.data

/**
 * Created by finn on 2022/3/21
 */

sealed class SealedClassEntity {
    data class SealedClass1(
        val name: String,
        val type: Int = 1,
    ) : SealedClassEntity()

    data class SealedClass2(
        val name: String,
        val type: Int = 2,
    ) : SealedClassEntity()

    data class SealedClass3(
        val name: String,
        val type: Int = 3,
    ) : SealedClassEntity()
}