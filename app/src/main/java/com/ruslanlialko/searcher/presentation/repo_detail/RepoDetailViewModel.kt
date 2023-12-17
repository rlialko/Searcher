package com.ruslanlialko.searcher.presentation.repo_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruslanlialko.searcher.domain.Resource
import com.ruslanlialko.searcher.domain.use_case.GetRepoDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RepoDetailViewModel @Inject constructor(
    private val getRepoDetailUseCase: GetRepoDetailUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RepoDetailState())
    val state: StateFlow<RepoDetailState> = _state.asStateFlow()

    fun loadDetail(name: String?, ownerLogin: String?) {

        if (ownerLogin != null && name != null) {
            getRepoDetailUseCase(ownerLogin, name).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = RepoDetailState(repoDetail = result.data!!)
                    }

                    is Resource.Error -> {
                        _state.value = RepoDetailState(error = result.error)
                    }

                    is Resource.Loading -> {
                        _state.value = RepoDetailState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}