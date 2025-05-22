package com.uka.appxplorer.presentation.features.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uka.appxplorer.domain.usecases.GetInstalledAppDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getInstalledAppDetailsUseCase: GetInstalledAppDetailsUseCase
) : ViewModel() {

    private val packageName: String = checkNotNull(savedStateHandle["packageName"])

    private val _state = MutableStateFlow(DetailsState())
    val state = _state.asStateFlow()

    private val _effect = Channel<DetailsEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        handleIntent(DetailsIntent.LoadDetails)
    }

    fun handleIntent(intent: DetailsIntent) {
        when (intent) {
            DetailsIntent.LoadDetails -> loadDetails()
            DetailsIntent.LaunchApp -> launchApp()
        }
    }

    private fun loadDetails() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = false)
            
            val details = getInstalledAppDetailsUseCase(packageName)
            if (details != null) {
                _state.value = _state.value.copy(
                    name = details.name,
                    version = details.version,
                    packageName = packageName,
                    checksum = details.checksum,
                    isLoading = false
                )
            } else {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = true
                )
            }
        }
    }

    private fun launchApp() {
        viewModelScope.launch {
            _effect.send(DetailsEffect.LaunchApp(packageName))
        }
    }
}