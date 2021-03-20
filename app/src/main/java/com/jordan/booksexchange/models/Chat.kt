package com.jordan.booksexchange.models

import java.sql.Timestamp

data class Chat(
    var text: String,
    var timestamp: Long,
    var senderId: String,
    var targetId: String
)