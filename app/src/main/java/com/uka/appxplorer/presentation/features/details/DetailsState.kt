package com.uka.appxplorer.presentation.features.details

data class DetailsState(
    val name: String = "",
    val version: String = "",
    val packageName: String = "",
    val checksum: String = "",
    val isLoading: Boolean = false,
    val error: Boolean = false
) 