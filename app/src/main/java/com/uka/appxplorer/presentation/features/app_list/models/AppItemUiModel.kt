package com.uka.appxplorer.presentation.features.app_list.models

import com.uka.appxplorer.domain.models.InstalledApp

data class AppItemUiModel(
    val name: String,
    val packageName: String
)

fun InstalledApp.toUiModel(): AppItemUiModel {
    return AppItemUiModel(
        name = name,
        packageName = packageName
    )
}