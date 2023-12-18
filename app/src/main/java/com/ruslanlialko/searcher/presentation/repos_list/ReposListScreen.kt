package com.ruslanlialko.searcher.presentation.repos_list

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ruslanlialko.searcher.R
import com.ruslanlialko.searcher.presentation.repos_list.components.RepoListItem
import com.ruslanlialko.searcher.presentation.ui.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReposListScreen(
    navController: NavController,
    viewModel: ReposListViewModel = hiltViewModel()
) {
    var searchText by rememberSaveable { mutableStateOf("") }
    val repoItems = viewModel.getRepos(searchText).collectAsLazyPagingItems()
    val ctx = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            TextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    //viewModel.search(inputText)
                },
                singleLine = true,
                label = { Text(text = stringResource(id = R.string.search_label)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(top = 16.dp))
            ) {
                items(
                    count = repoItems.itemCount,
                    key = { it }
                ) {
                    val repo = repoItems[it]!!
                    RepoListItem(repoItem = repo) {
                        navController.navigate(
                            Screen.RepoDetail.withArgs(
                                repo.name,
                                repo.ownerLogin
                            )
                        )
                    }
                }
                when (repoItems.loadState.refresh) { //FIRST LOAD
                    is LoadState.Error -> {
                        Toast.makeText(ctx, R.string.many_requests_error, Toast.LENGTH_LONG).show()
                    }

                    is LoadState.Loading -> { // Loading UI
                        item {
                            Column(
                                modifier = Modifier
                                    .fillParentMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                CircularProgressIndicator(color = Color.Black)
                            }
                        }
                    }

                    else -> {}
                }

                when (repoItems.loadState.append) { // Pagination
                    is LoadState.Error -> {
                        Toast.makeText(ctx, R.string.many_requests_error, Toast.LENGTH_LONG).show()
                    }

                    is LoadState.Loading -> { // Pagination Loading UI
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                CircularProgressIndicator(color = Color.Black)
                            }
                        }
                    }

                    else -> {}

                }
            }
        }
    }
}
