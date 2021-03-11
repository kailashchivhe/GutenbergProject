package com.kai.gutenbergproject.model

data class Gutenberg(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)