package com.ruslanlialko.searcher.domain.model

import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

private const val SHORT_DESCRIPTION_MAX_LENGTH = 50

data class RepoItem(
    val name: String,
    val description: String,
    val ownerLogin: String,
    val language: String?,
    val createdAt: String,
    val openIssuesCount: Int,
    val stars: Int,
) {

    fun getShortDescription(): String {
        return description.substring(
            0,
            description.length.coerceAtMost(SHORT_DESCRIPTION_MAX_LENGTH)
        )
    }

    fun getStarsFormatted(): String {
        val suffix = charArrayOf(' ', 'K', 'M', 'B', 'T', 'P', 'E')
        val value = floor(log10(stars.toDouble())).toInt()
        val base = value / 3
        return if (value >= 3 && base < suffix.size) {
            DecimalFormat("#0.0").format(
                stars / 10.0.pow((base * 3).toDouble())
            ) + suffix[base]
        } else {
            DecimalFormat("#,##0").format(stars)
        }
    }
}