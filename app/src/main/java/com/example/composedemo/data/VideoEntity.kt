package com.example.composedemo.data

data class VideoEntity(
    val videoId:String?="",
    val text:String?="",
    val des:String?="",
    val isAd:Boolean?=false,
    val type :Int= 0,
)
