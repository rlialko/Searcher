package com.ruslanlialko.searcher.domain.model

data class RepoItem(
    val name: String,
    val description: String,
    val ownerLogin: String,
    val language: String?,
    val createdAt: String,
    val openIssuesCount: Int,
    val stars: Int,
)