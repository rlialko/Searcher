package com.ruslanlialko.searcher.presentation.repos_list

import com.ruslanlialko.searcher.domain.model.RepoItem

data class ReposListState(
    val repos: List<RepoItem> = emptyList(),
    val totalCount: Int = 0,
    val isLoading: Boolean = false,
    val isInputValid: Boolean = true,
    val error: Throwable? = null
)