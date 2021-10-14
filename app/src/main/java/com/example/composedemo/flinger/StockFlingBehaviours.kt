package com.example.composedemo.flinger

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.runtime.Composable
import com.example.composedemo.flinger.flings.flingBehavior
import io.iamjosephmj.flinger.configs.FlingConfiguration

object StockFlingBehaviours {

        @Composable
        fun getAndroidNativeScroll(): FlingBehavior = ScrollableDefaults.flingBehavior()


        @Composable
        fun smoothScroll(): FlingBehavior = flingBehavior(
            scrollConfiguration = FlingConfiguration.Builder()
                .build()
        )

        @Composable
        fun presetOne(): FlingBehavior = flingBehavior(
            scrollConfiguration = FlingConfiguration.Builder()
                .scrollViewFriction(0.04f)
                .build()
        )

        @Composable
        fun presetTwo(): FlingBehavior = flingBehavior(
            scrollConfiguration = FlingConfiguration.Builder()
                .splineInflection(0.16f)
                .build()
        )

        @Composable
        fun presetThree(): FlingBehavior = flingBehavior(
            scrollConfiguration = FlingConfiguration.Builder()
                .decelerationFriction(0.5f)
                .build()
        )

        @Composable
        fun presetFour(): FlingBehavior = flingBehavior(
            scrollConfiguration = FlingConfiguration.Builder()
                .decelerationFriction(0.6f)
                .splineInflection(.4f)
                .build()
        )

        @Composable
        fun presetFive(): FlingBehavior = flingBehavior(
            scrollConfiguration = FlingConfiguration.Builder()
                .scrollViewFriction(.09f)
                .decelerationFriction(0.015f)
                .build()
        )

    }