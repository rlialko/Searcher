package com.ruslanlialko.searcher.presentation.repos_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.ruslanlialko.searcher.R
import com.ruslanlialko.searcher.domain.exception.NoInternetException
import com.ruslanlialko.searcher.domain.exception.TooManyRequestsException
import com.ruslanlialko.searcher.presentation.repos_list.components.RepoListItem
import com.ruslanlialko.searcher.presentation.ui.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReposListScreen(
    navController: NavController,
    viewModel: ReposListViewModel = hiltViewModel()
) {
    val state: ReposListState by viewModel.state.collectAsStateWithLifecycle()
    var inputText by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column {
            TextField(
                value = inputText,
                onValueChange = {
                    inputText = it
                    viewModel.search(inputText)
                },
                singleLine = true,
                label = { Text(text = stringResource(id = R.string.search_label)) },
                isError = state.error != null,
                supportingText = {
                    state.error?.let {
                        Text(
                            text = when (state.error) {
                                is TooManyRequestsException -> stringResource(id = R.string.many_requests_error)
                                is NoInternetException -> stringResource(id = R.string.no_internet_error)
                                else -> stringResource(id = R.string.general_error)
                            },
                            Modifier.fillMaxWidth()
                        )
                    }
                    if (!state.isInputValid) {
                        Text(
                            text = stringResource(id = R.string.validation_error),
                            Modifier.fillMaxWidth()
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(top = 16.dp))
            ) {
                items(state.repos) { repo ->
                    RepoListItem(repoItem = repo) {
                        navController.navigate(
                            Screen.RepoDetail.withArgs(
                                repo.name,
                                repo.ownerLogin
                            )
                        )
                    }
                }
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}