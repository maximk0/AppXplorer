package com.uka.appxplorer.domain.models

data class InstalledApp(
    val name: String,
    val packageName: String,
    val version: String = "",
    val checksum: String = "",
)