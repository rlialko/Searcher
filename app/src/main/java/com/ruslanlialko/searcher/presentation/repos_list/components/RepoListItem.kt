package com.ruslanlialko.searcher.presentation.repos_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruslanlialko.searcher.domain.model.RepoItem

@Composable
fun RepoListItem(
    repoItem: RepoItem,
    onClick: (RepoItem) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick(repoItem) }
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            Text(
                text = repoItem.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Text(
                text = repoItem.getShortDescription(),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )

        Text(
            text = repoItem.getStarsFormatted(),
            fontSize = 18.sp,
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.CenterVertically)
                .padding(end = 16.dp)
        )
    }
    Divider()
}