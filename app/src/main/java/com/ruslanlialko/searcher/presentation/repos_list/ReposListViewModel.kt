package com.ruslanlialko.searcher.presentation.repos_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruslanlialko.searcher.domain.Resource
import com.ruslanlialko.searcher.domain.use_case.GetReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ReposListViewModel @Inject constructor(
    private val getReposUseCase: GetReposUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ReposListState())
    val state: StateFlow<ReposListState> = _state.asStateFlow()

    private var searchJob: Job? = null

    fun search(query: String) {
        searchJob?.cancel()
        if (query.isEmpty()) {
            _state.value = ReposListState()
            return
        }
        if (query.length < 3) {
            _state.value = ReposListState(isInputValid = false)
            return
        }
        //todo page
        searchJob = getReposUseCase(query, 0, 20).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ReposListState(repos = result.data?.repos ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = ReposListState(error = result.error)
                }

                is Resource.Loading -> {
                    _state.value = ReposListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}