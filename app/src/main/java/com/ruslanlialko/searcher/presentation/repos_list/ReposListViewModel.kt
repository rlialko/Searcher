package com.ruslanlialko.searcher.presentation.repos_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ruslanlialko.searcher.domain.model.RepoItem
import com.ruslanlialko.searcher.domain.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class ReposListViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel() {

    fun getRepos(query: String): Flow<PagingData<RepoItem>> {
        if (query.length < 3) {
            return flow { }
        }
        return repository.getRepos(query).cachedIn(viewModelScope)
    }
}