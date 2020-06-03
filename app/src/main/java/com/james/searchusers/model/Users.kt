package com.james.searchusers.model

data class Users(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
)