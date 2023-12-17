package com.ruslanlialko.searcher.presentation.repo_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun RepoDetailScreen(
    name: String?,
    ownerLogin: String?,
    viewModel: RepoDetailViewModel = hiltViewModel()
) {
    val state: RepoDetailState by viewModel.state.collectAsStateWithLifecycle()

    viewModel.loadDetail(name, ownerLogin)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column {

            Text(
                text = name ?: "nothing",
                Modifier.fillMaxWidth()
            )

            Text(
                text = ownerLogin ?: "nothing",
                Modifier.fillMaxWidth()

            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}