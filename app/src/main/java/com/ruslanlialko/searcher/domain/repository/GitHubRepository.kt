package com.ruslanlialko.searcher.domain.repository

import com.ruslanlialko.searcher.data.dto.RepoDetailDto
import com.ruslanlialko.searcher.data.dto.ReposDto

interface GitHubRepository {

    suspend fun getRepositories(query: String, page: Int, perPage: Int): ReposDto

    suspend fun getRepoDetails(owner: String, repo: String): RepoDetailDto
}