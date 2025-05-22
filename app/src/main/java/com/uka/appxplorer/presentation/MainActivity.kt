package com.uka.appxplorer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.uka.appxplorer.core.theme.AppXplorerTheme
import com.uka.appxplorer.presentation.navigation.AppXplorerHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppXplorerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppXplorerHost(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
