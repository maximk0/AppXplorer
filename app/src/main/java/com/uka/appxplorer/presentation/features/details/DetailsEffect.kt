package com.uka.appxplorer.presentation.features.details

sealed class DetailsEffect {
    data class LaunchApp(val packageName: String) : DetailsEffect()
} 