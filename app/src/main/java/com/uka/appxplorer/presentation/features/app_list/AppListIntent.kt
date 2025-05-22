package com.uka.appxplorer.presentation.features.app_list

sealed class AppListIntent {
    data class OpenAppDetails(val packageName: String) : AppListIntent()
    data object LoadApps : AppListIntent()
} 