package com.example.composedemo.ui.page.template.net

sealed class ConnectionState{
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}