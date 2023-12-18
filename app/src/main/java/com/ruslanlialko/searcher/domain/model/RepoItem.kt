package com.ruslanlialko.searcher.domain.model

import java.text.DecimalFormat

data class RepoItem(
    val name: String,
    val description: String,
    val ownerLogin: String,
    val language: String?,
    val createdAt: String,
    val openIssuesCount: Int,
    val stars: Int,
) {
    fun getStarsFormatted(): String {
        val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
        val value = Math.floor(Math.log10(stars.toDouble())).toInt()
        val base = value / 3
        return if (value >= 3 && base < suffix.size) {
            DecimalFormat("#0.0").format(
                stars / Math.pow(
                    10.0,
                    (base * 3).toDouble()
                )
            ) + suffix[base]
        } else {
            DecimalFormat("#,##0").format(stars)
        }
    }
}