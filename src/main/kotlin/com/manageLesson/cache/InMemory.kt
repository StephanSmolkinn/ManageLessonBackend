package com.manageLesson.cache

import com.manageLesson.features.register.RegisterReceiveRemote

data class TokenCache(
    val login: String,
    val token: String,
)

object InMemory {
    val userList: MutableList<RegisterReceiveRemote> = mutableListOf()
    val token :MutableList<TokenCache> = mutableListOf()
}