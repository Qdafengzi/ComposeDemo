package com.example.composedemo.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.composedemo.R
import com.example.composedemo.app.appViewModel
import kotlinx.coroutines.flow.collect

class StateTwoActivity : AppCompatActivity() {
    var text = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_two)

        val textOne = findViewById<TextView>(R.id.mTvContent)
        val textTwo = findViewById<TextView>(R.id.mTvContentTwo)
        val button = findViewById<Button>(R.id.mButton)

        button.setOnClickListener {
            text++
            appViewModel.setStateOne("one  $text")
            appViewModel.setStateTwo("two  $text")
        }


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