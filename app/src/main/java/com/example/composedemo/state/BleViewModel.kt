package com.example.composedemo.state

import android.annotation.SuppressLint
import android.bluetooth.BluetoothManager
import android.content.Context
import androidx.bluetooth.BluetoothLe
import androidx.bluetooth.ScanFilter
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.composedemo.utils.XLogger
import java.util.UUID

class BleViewModel (val context:Context): ViewModel() {


    var blueIsOpen by mutableStateOf(false)

    var deviceList  = mutableStateSetOf<String>()

    init {
        getBluetoothState(context)
    }


    @SuppressLint("ObsoleteSdkInt")
    fun getBluetoothState(context: Context) {
        // 获取BluetoothAdapter
        val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        val adapter = bluetoothManager.adapter
        // 检查蓝牙是否开启
        blueIsOpen = adapter.isEnabled ?: false
    }

    @SuppressLint("MissingPermission")
   suspend fun scan(){

        val bluetoothLe = BluetoothLe(context =context)
        // 根据serviceUuid和deviceName 过滤条件进行扫描
        val filters = listOf(
            ScanFilter(serviceDataUuid = UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb"))
        )
        val scanFlow = bluetoothLe.scan(filters)
        // 扫描到的结果
        scanFlow.collect {
            if(!it.device.name.isNullOrBlank()){
                XLogger.d(" scan result name:${it.device.name} rssi:${it.rssi}  serviceSolicitationUuids:${it.serviceSolicitationUuids} serviceUuids:${it.serviceUuids}")
                deviceList.add(it.device.name ?: "")
            }
        }
    }

}