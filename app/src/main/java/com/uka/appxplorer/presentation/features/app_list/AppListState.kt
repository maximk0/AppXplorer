package com.uka.appxplorer.presentation.features.app_list

import com.uka.appxplorer.presentation.features.app_list.models.AppItemUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class AppListState(
    val apps: ImmutableList<AppItemUiModel> = persistentListOf(),
    val isLoading: Boolean = false
)