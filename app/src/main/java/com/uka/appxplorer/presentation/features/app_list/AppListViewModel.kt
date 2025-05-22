package com.uka.appxplorer.presentation.features.app_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uka.appxplorer.domain.usecases.GetInstalledAppsUseCase
import com.uka.appxplorer.presentation.features.app_list.models.AppItemUiModel
import com.uka.appxplorer.presentation.features.app_list.models.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(
    private val getInstalledAppsUseCase: GetInstalledAppsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(AppListState())
    val state: StateFlow<AppListState> = _state.asStateFlow()

    private val _effect = Channel<AppListEffect>()
    val effect = _effect.receiveAsFlow()

    fun handleIntent(intent: AppListIntent) {
        when (intent) {
            is AppListIntent.LoadApps -> loadApps()
            is AppListIntent.OpenAppDetails -> openAppDetails(intent.packageName)
        }
    }

    private fun openAppDetails(packageName: String) {
        viewModelScope.launch {
            _effect.send(AppListEffect.NavigateToDetails(packageName))
        }
    }

    private fun loadApps() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            getInstalledAppsUseCase()
                .map { apps ->
                    apps.map { it.toUiModel() }.toImmutableList()
                }
                .collect { uiApps ->
                    _state.value = _state.value.copy(
                        apps = uiApps,
                        isLoading = false
                    )
                }
        }
    }

}
