package com.ruslanlialko.searcher.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ruslanlialko.searcher.presentation.repo_detail.RepoDetailScreen
import com.ruslanlialko.searcher.presentation.repos_list.ReposListScreen
import com.ruslanlialko.searcher.presentation.ui.theme.RepoSearcherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RepoSearcherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ReposList.route
                    ) {
                        composable(route = Screen.ReposList.route) {
                            ReposListScreen(navController = navController)
                        }
                        composable(route = Screen.RepoDetail.route + "/{$DETAIL_ARGUMENT_NAME}/{$DETAIL_ARGUMENT_OWNER_LOGIN}",
                            arguments = listOf(
                                navArgument(DETAIL_ARGUMENT_NAME) {
                                    type = NavType.StringType
                                },
                                navArgument(DETAIL_ARGUMENT_OWNER_LOGIN) {
                                    type = NavType.StringType
                                }
                            )
                        ) { entry ->
                            RepoDetailScreen(
                                name = entry.arguments?.getString(DETAIL_ARGUMENT_NAME),
                                ownerLogin = entry.arguments?.getString(DETAIL_ARGUMENT_OWNER_LOGIN),
                            )
                        }
                    }
                }
            }
        }
    }
}