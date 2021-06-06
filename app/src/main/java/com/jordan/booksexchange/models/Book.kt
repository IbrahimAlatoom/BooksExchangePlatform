package com.jordan.booksexchange.models

enum class University() {
    Just, JU, Psut, ZU,
    YU,
    BAU,
    IU,
    WISE,
    MUTAH,
    AABU,
    HU,
    AHU,
    AAU,
    UOP,
    Other,
}

class Book(
    val name: String,
    val publisherId: String?,
    val publisherName: String,
    val bookId: String,
    val university: University?,
    val topic: BookTopic?,
    val description: String,
    val imageUrl: String
) {
    constructor() : this("", "", "", "", null, null, "", "")
}
