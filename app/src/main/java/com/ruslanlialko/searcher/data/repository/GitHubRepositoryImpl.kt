package com.ruslanlialko.searcher.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.ruslanlialko.searcher.data.dto.RepoDetailDto
import com.ruslanlialko.searcher.data.dto.ReposDto
import com.ruslanlialko.searcher.data.remote.GitHubApi
import com.ruslanlialko.searcher.domain.repository.GetReposPagingSource
import com.ruslanlialko.searcher.domain.repository.GitHubRepository
import com.ruslanlialko.searcher.domain.repository.PER_PAGE
import javax.inject.Inject

class GitHubRepositoryImpl @Inject constructor(private val gitHubApi: GitHubApi) :
    GitHubRepository {

    override suspend fun getRepositories(query: String, page: Int, perPage: Int): ReposDto {
        return gitHubApi.getRepositories(query, page, perPage)
    }

    override suspend fun getRepoDetails(owner: String, repo: String): RepoDetailDto {
        return gitHubApi.getRepoDetail(owner, repo)
    }

    override fun getRepos(query: String) = Pager(
        config = PagingConfig(
            pageSize = PER_PAGE,
        ),
        pagingSourceFactory = {
            GetReposPagingSource(query, gitHubApi)
        }
    ).flow

}