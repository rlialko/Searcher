package com.ruslanlialko.searcher.domain.use_case

import com.ruslanlialko.searcher.data.dto.toRepos
import com.ruslanlialko.searcher.domain.Resource
import com.ruslanlialko.searcher.domain.exception.NoInternetException
import com.ruslanlialko.searcher.domain.exception.TooManyRequestsException
import com.ruslanlialko.searcher.domain.model.Repos
import com.ruslanlialko.searcher.domain.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetReposUseCase @Inject constructor(private val gitHubRepository: GitHubRepository) {

    operator fun invoke(query: String, page: Int, perPage: Int): Flow<Resource<Repos>> = flow {
        emit(Resource.Loading())
        try {
            val repos = gitHubRepository.getRepositories(query, page, perPage).toRepos()
            emit(Resource.Success(repos))
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