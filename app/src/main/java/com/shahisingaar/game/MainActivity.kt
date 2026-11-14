package com.shahisingaar.game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import com.shahisingaar.game.state.GameViewModel
import com.shahisingaar.game.ui.GameScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val viewModel = remember { GameViewModel() }
                GameScreen(viewModel = viewModel)
            }
        }
    }
}
