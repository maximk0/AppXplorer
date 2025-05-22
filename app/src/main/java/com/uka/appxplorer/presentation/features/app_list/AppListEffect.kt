package com.uka.appxplorer.presentation.features.app_list

sealed class AppListEffect {
    data class NavigateToDetails(val packageName: String) : AppListEffect()
} 