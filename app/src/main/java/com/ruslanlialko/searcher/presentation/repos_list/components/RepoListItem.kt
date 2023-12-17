package com.ruslanlialko.searcher.presentation.repos_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ruslanlialko.searcher.domain.model.RepoItem

@Composable
fun RepoListItem(
    repoItem: RepoItem,
    onClick: (RepoItem) -> Unit
) {
    Text(
        text = repoItem.name,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick(repoItem) }
    )
}