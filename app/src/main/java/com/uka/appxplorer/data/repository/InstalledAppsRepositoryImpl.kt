package com.uka.appxplorer.data.repository

import com.uka.appxplorer.data.datasource.InstalledAppsDataSource
import com.uka.appxplorer.domain.models.InstalledApp
import com.uka.appxplorer.domain.repositories.InstalledAppsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class InstalledAppsRepositoryImpl @Inject constructor(
    private val dataSource: InstalledAppsDataSource
) : InstalledAppsRepository {

    override fun getInstalledApps(): Flow<List<InstalledApp>> = flow {
        emit(dataSource.getInstalledApplications())
    }.flowOn(Dispatchers.IO)

    override suspend fun getInstalledAppById(packageName: String): InstalledApp? {
        return dataSource.getApplicationByPackageName(packageName)
    }
}