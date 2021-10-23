package com.example.composedemo.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.composedemo.R
import com.example.composedemo.app.appViewModel
import kotlinx.coroutines.flow.collect

class StateFlowActivity : AppCompatActivity() {
    private val TAG = "StateFlowActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_flow)

        findViewById<Button>(R.id.mButton).setOnClickListener {
            startActivity(Intent(this,StateTwoActivity::class.java))
        }

        val textOne =  findViewById<TextView>(R.id.mTvContent)
        val textTwo =  findViewById<TextView>(R.id.mTvContentTwo)

        lifecycleScope.launchWhenCreated {
            appViewModel.mStateOne.collect {
                textOne.text = it.content
            }
        }

        lifecycleScope.launchWhenCreated {
            appViewModel.mStateTwo.collect {
                textTwo.text = it.content
            }
        }





    }
}