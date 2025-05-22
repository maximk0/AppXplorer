package com.uka.appxplorer.di

import android.content.Context
import com.uka.appxplorer.data.datasource.InstalledAppsDataSource
import com.uka.appxplorer.data.repository.InstalledAppsRepositoryImpl
import com.uka.appxplorer.domain.repositories.InstalledAppsRepository
import com.uka.appxplorer.domain.usecases.GetInstalledAppDetailsUseCase
import com.uka.appxplorer.domain.usecases.GetInstalledAppsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideInstalledAppsDataSource(
        @ApplicationContext context: Context
    ): InstalledAppsDataSource {
        return InstalledAppsDataSource(context)
    }

    @Provides
    @Singleton
    fun provideInstalledAppsRepository(
        dataSource: InstalledAppsDataSource
    ): InstalledAppsRepository {
        return InstalledAppsRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideGetInstalledAppsUseCase(
        repository: InstalledAppsRepository
    ): GetInstalledAppsUseCase {
        return GetInstalledAppsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetInstalledAppDetailsUseCase(
        repository: InstalledAppsRepository
    ): GetInstalledAppDetailsUseCase {
        return GetInstalledAppDetailsUseCase(repository)
    }

} 