package com.ruslanlialko.searcher.domain.use_case

import com.ruslanlialko.searcher.data.dto.toRepoDetail
import com.ruslanlialko.searcher.domain.Resource
import com.ruslanlialko.searcher.domain.exception.NoInternetException
import com.ruslanlialko.searcher.domain.exception.TooManyRequestsException
import com.ruslanlialko.searcher.domain.model.RepoDetail
import com.ruslanlialko.searcher.domain.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRepoDetailUseCase @Inject constructor(private val gitHubRepository: GitHubRepository) {

    operator fun invoke(owner: String, repo: String): Flow<Resource<RepoDetail>> = flow {
        emit(Resource.Loading())
        try {
            val repoDetail = gitHubRepository.getRepoDetails(owner, repo).toRepoDetail()
            emit(Resource.Success(repoDetail))
        } catch (e: HttpException) {
            if (e.code() == 403) {
                emit(Resource.Error(TooManyRequestsException()))
                return@flow
            }
            emit(Resource.Error(e))
        } catch (e: IOException) {
            emit(Resource.Error(NoInternetException()))
        }
    }
}