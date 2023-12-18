package com.ruslanlialko.searcher.domain.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ruslanlialko.searcher.data.dto.toRepos
import com.ruslanlialko.searcher.data.remote.GitHubApi
import com.ruslanlialko.searcher.domain.model.RepoItem
import javax.inject.Inject

const val PER_PAGE = 10

class GetReposPagingSource @Inject constructor(
    private val query: String,
    private val gitHubApi: GitHubApi
) : PagingSource<Int, RepoItem>() {

    override fun getRefreshKey(state: PagingState<Int, RepoItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoItem> {
        return try {
            val page = params.key ?: 1
            val response =
                gitHubApi.getRepositories(query = query, page = page, perPage = PER_PAGE)
                    .toRepos()

            LoadResult.Page(
                data = response.repos,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.repos.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}