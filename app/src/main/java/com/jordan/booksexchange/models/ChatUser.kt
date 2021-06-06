package com.jordan.booksexchange.models

data class ChatUser(var userId: String, var name: String) {
    constructor():this("", "")
}