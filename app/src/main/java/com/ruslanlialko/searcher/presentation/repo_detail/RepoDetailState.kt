package com.ruslanlialko.searcher.presentation.repo_detail

import com.ruslanlialko.searcher.domain.model.RepoDetail

data class RepoDetailState(
    val repoDetail: RepoDetail = RepoDetail("", ""),
    val isLoading: Boolean = false,
    val error: Throwable? = null
)