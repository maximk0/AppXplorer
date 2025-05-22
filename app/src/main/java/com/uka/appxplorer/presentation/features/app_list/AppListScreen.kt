package com.uka.appxplorer.presentation.features.app_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.uka.appxplorer.presentation.features.app_list.models.AppItemUiModel
import com.uka.appxplorer.presentation.features.app_list.widgets.AppItem
import kotlinx.collections.immutable.ImmutableList

@Composable
fun AppListScreen(
    isLoading: Boolean,
    apps: ImmutableList<AppItemUiModel>,
    onIntent: (AppListIntent) -> Unit,
) {
    LaunchedEffect(Unit) {
        onIntent(AppListIntent.LoadApps)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            val context = LocalContext.current

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    items = apps,
                    key = { it.packageName }
                ) { app ->
                    AppItem(
                        icon = context.packageManager.getApplicationIcon(app.packageName),
                        name = app.name,
                        onAppClick = { onIntent(AppListIntent.OpenAppDetails(app.packageName)) }
                    )
                }
            }
        }
    }
}


