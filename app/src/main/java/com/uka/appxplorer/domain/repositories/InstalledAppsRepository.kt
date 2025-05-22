package com.uka.appxplorer.domain.repositories

import com.uka.appxplorer.domain.models.InstalledApp
import kotlinx.coroutines.flow.Flow

interface InstalledAppsRepository {
    fun getInstalledApps(): Flow<List<InstalledApp>>
    suspend fun getInstalledAppById(packageName: String): InstalledApp?
}