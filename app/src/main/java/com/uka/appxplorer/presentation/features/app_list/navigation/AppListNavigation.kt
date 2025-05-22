package com.uka.appxplorer.presentation.features.app_list.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uka.appxplorer.presentation.features.app_list.AppListEffect
import com.uka.appxplorer.presentation.features.app_list.AppListIntent
import com.uka.appxplorer.presentation.features.app_list.AppListScreen
import com.uka.appxplorer.presentation.features.app_list.AppListViewModel
import kotlinx.serialization.Serializable

@Serializable
object AppListDestination

fun NavGraphBuilder.appList(
    navigateToDetails: (String) -> Unit
) {
    composable<AppListDestination> {
        val viewModel: AppListViewModel = hiltViewModel()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    is AppListEffect.NavigateToDetails -> navigateToDetails(effect.packageName)
                }
            }
        }

        AppListScreen(
            isLoading = state.isLoading,
            apps = state.apps,
            onIntent = viewModel::handleIntent,
        )
    }
}