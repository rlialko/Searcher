package com.ruslanlialko.searcher.data.dto

import com.ruslanlialko.searcher.domain.model.Repos

data class ReposDto(
    val incomplete_results: Boolean,
    val items: List<ItemDto>,
    val total_count: Int
)


fun ReposDto.toRepos(): Repos{
    return Repos(totalCount =  total_count, items.map { it.toRepoItem() })
}