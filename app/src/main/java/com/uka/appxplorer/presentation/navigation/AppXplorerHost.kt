package com.uka.appxplorer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.uka.appxplorer.presentation.features.app_list.navigation.AppListDestination
import com.uka.appxplorer.presentation.features.app_list.navigation.appList
import com.uka.appxplorer.presentation.features.details.navigation.DetailsDestination
import com.uka.appxplorer.presentation.features.details.navigation.appDetails

@Composable
fun AppXplorerHost(
    modifier: Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AppListDestination,
    ) {
        appList { packageName ->
            navController.navigate(DetailsDestination(packageName))
        }

        appDetails(
            onBackClick = {
                navController.popBackStack()
            }
        )
    }
}