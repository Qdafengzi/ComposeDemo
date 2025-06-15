package com.example.composedemo.ui.page.home

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.composedemo.ui.widget.CommonToolbar


data object Navigation3Home
data class Navigation3Product(val id: String)

@Composable
fun Navigation3Page(navCtrl: NavHostController, title: String) {
    CommonToolbar(navCtrl, title) {
        val backStack = remember { mutableStateListOf<Any>(Navigation3Home) }
        NavDisplay(
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() },

            transitionSpec = {
                ContentTransform(
                    slideInHorizontally(animationSpec = tween(300), initialOffsetX = { it }),
                    slideOutHorizontally(animationSpec = tween(300), targetOffsetX = { -it }),

//                    slideInHorizontally(animationSpec = tween(300), initialOffsetX = { -it }),
//                    slideOutHorizontally(animationSpec = tween(300), targetOffsetX = { it }),
                )
            },
            popTransitionSpec = {
                ContentTransform(
//                    slideInHorizontally(animationSpec = tween(300), initialOffsetX = { it }),
//                    slideOutHorizontally(animationSpec = tween(300), targetOffsetX = { -it }),
                    slideInHorizontally(animationSpec = tween(300), initialOffsetX = { -it }),
                    slideOutHorizontally(animationSpec = tween(300), targetOffsetX = { it }),
                )
            },

            entryProvider = { route ->
                when (route) {
                    is Navigation3Home -> NavEntry(route) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text("Welcome to Nav3")
                            Button(onClick = {
                                backStack.add(Navigation3Product("123"))
                            }) {
                                Text("Click to navigate")
                            }
                        }
                    }

                    is Navigation3Product -> NavEntry(route) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text("Product ${route.id} ")
                        }
                    }

                    else -> NavEntry(Unit) { Text("Unknown route: $route") }
                }
            }
        )
    }
}