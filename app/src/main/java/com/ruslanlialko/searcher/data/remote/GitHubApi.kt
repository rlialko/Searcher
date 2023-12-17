package com.ruslanlialko.searcher.data.remote

import com.ruslanlialko.searcher.data.dto.RepoDetailDto
import com.ruslanlialko.searcher.data.dto.ReposDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    @GET("/search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): ReposDto

    @GET("/repos/{owner}/{repo}}")
    suspend fun getRepoDetail(
        @Path("owner") query: String,
        @Path("repo") page: String,
    ): RepoDetailDto

}