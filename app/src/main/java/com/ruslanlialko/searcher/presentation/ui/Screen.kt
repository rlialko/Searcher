package com.ruslanlialko.searcher.presentation.ui

sealed class Screen(val route: String) {
    object ReposList : Screen("repos_list_screen")
    object RepoDetail : Screen("repo_detail_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
