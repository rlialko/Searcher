package com.ruslanlialko.searcher.presentation.repos_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ruslanlialko.searcher.domain.model.RepoItem

@Composable
fun RepoListItem(
    repoItem: RepoItem,
    onClick: (RepoItem) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(repoItem) }
    ) {

        Text(
            text = repoItem.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f)
        )

        Text(
            text = repoItem.stars.toString(),
            modifier = Modifier.width(60.dp)
        )
    }
}