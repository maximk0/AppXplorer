package com.uka.appxplorer.presentation.features.details

sealed class DetailsIntent {
    data object LoadDetails : DetailsIntent()
    data object LaunchApp : DetailsIntent()
} 