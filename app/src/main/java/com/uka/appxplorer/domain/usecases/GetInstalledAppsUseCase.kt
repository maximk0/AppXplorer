package com.uka.appxplorer.domain.usecases

import com.uka.appxplorer.domain.models.InstalledApp
import com.uka.appxplorer.domain.repositories.InstalledAppsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInstalledAppsUseCase @Inject constructor(
    private val repository: InstalledAppsRepository
) {
    operator fun invoke(): Flow<List<InstalledApp>> = repository.getInstalledApps()
}