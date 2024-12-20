package com.manageLesson.features.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseRemote(
    val token: String
)
