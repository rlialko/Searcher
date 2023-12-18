package com.ruslanlialko.searcher.presentation.ui


const val DETAIL_ARGUMENT_NAME = "name"
const val DETAIL_ARGUMENT_OWNER_LOGIN = "ownerLogin"
sealed class Screen(val route: String) {
    object ReposList : Screen("repos_list_screen")
    object RepoDetail : Screen("repo_detail_screen") {
        fun withArgs(name: String, ownerLogin: String): String {
            return "$route/$name/$ownerLogin"
        }
    }
}
