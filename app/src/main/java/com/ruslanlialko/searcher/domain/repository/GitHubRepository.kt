package com.ruslanlialko.searcher.domain.repository

import androidx.paging.PagingData
import com.ruslanlialko.searcher.data.dto.RepoDetailDto
import com.ruslanlialko.searcher.data.dto.ReposDto
import com.ruslanlialko.searcher.domain.model.RepoItem
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {

    suspend fun getRepositories(query: String, page: Int, perPage: Int): ReposDto

    suspend fun getRepoDetails(owner: String, repo: String): RepoDetailDto

    fun getRepos(query: String): Flow<PagingData<RepoItem>>
}