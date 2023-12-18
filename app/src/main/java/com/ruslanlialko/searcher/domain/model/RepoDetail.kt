package com.ruslanlialko.searcher.domain.model

data class RepoDetail(
    val name: String = "",
    val description: String = "",
    val createdAt: String = "",
    val openIssuesCount: Int = 0,
    val stars: Int = 0,
)
