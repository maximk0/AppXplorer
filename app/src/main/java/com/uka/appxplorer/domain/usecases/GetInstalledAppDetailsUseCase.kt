package com.uka.appxplorer.domain.usecases

import com.uka.appxplorer.domain.models.InstalledApp
import com.uka.appxplorer.domain.repositories.InstalledAppsRepository
import javax.inject.Inject

class GetInstalledAppDetailsUseCase @Inject constructor(
    private val repository: InstalledAppsRepository
) {
    suspend operator fun invoke(id: String): InstalledApp? = repository.getInstalledAppById(id)
}