package com.uka.appxplorer.presentation.features.details.navigation

import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.uka.appxplorer.R
import com.uka.appxplorer.presentation.features.details.DetailsEffect
import com.uka.appxplorer.presentation.features.details.DetailsScreen
import com.uka.appxplorer.presentation.features.details.DetailsViewModel
import kotlinx.serialization.Serializable

@Serializable
data class DetailsDestination(
    val packageName: String
)

fun NavGraphBuilder.appDetails(
    onBackClick: () -> Unit
) {
    composable<DetailsDestination> {
        val context = LocalContext.current
        val viewModel: DetailsViewModel = hiltViewModel()
        val state by viewModel.state.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    is DetailsEffect.LaunchApp -> {
                        val launchIntent = context.packageManager.getLaunchIntentForPackage(effect.packageName)
                        if (launchIntent != null) {
                            launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            context.startActivity(launchIntent)
                        } else {
                            Toast.makeText(
                                context,
                                context.getString(R.string.details_cannot_launch_system_app),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

        DetailsScreen(
            isLoading = state.isLoading,
            name = state.name,
            version = state.version,
            packageName = state.packageName,
            checksum = state.checksum,
            error = state.error,
            onIntent = viewModel::handleIntent,
            onBackClick = onBackClick,
        )
    }
}