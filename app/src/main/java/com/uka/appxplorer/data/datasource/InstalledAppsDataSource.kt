package com.uka.appxplorer.data.datasource

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.uka.appxplorer.domain.models.InstalledApp
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.security.MessageDigest
import javax.inject.Inject

class InstalledAppsDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val packageManager: PackageManager = context.packageManager

    fun getInstalledApplications(): List<InstalledApp> {
        return packageManager.getInstalledPackages(PackageManager.GET_META_DATA)
            .map {
                InstalledApp(
                    name = it.getName(packageManager),
                    packageName = it.packageName
                )
            }
    }

    fun getApplicationByPackageName(packageName: String): InstalledApp? {
        return runCatching {
            packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA).run {
                val apkPath = applicationInfo?.sourceDir ?: return null
                val versionName = versionName ?: return null

                toDomain(
                    name = getName(packageManager),
                    version = versionName,
                    checksum = calculateApkChecksum(apkPath)
                )
            }
        }.getOrNull()
    }

    private fun calculateApkChecksum(apkPath: String): String {
        return runCatching {
            val md = MessageDigest.getInstance(HASH_ALGORITHM)
            return File(apkPath).inputStream().use { fis ->
                val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
                generateSequence {
                    when (val bytesRead = fis.read(buffer)) {
                        END_OF_STREAM -> null
                        else -> bytesRead
                    }
                }.forEach { bytesRead -> md.update(buffer, BUFFER_OFFSET, bytesRead) }
                md.digest().joinToString(EMPTY) { HEX_FORMAT.format(it) }
            }
        }.getOrDefault(EMPTY)
    }


    companion object {
        private const val HASH_ALGORITHM = "MD5"
        private const val HEX_FORMAT = "%02x"
        private const val EMPTY = ""

        private const val DEFAULT_BUFFER_SIZE = 8192
        private const val END_OF_STREAM = -1
        private const val BUFFER_OFFSET = 0
    }
}

private fun PackageInfo.toDomain(
    name: String,
    version: String,
    checksum: String
): InstalledApp {

    return InstalledApp(
        name = name,
        packageName = packageName,
        version = version,
        checksum = checksum
    )
}

private fun PackageInfo.getName(packageManager: PackageManager): String {
    return applicationInfo?.loadLabel(packageManager).toString()
}