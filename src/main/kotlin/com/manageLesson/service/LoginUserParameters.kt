package com.manageLesson.service

import kotlinx.serialization.Serializable

@Serializable
data class LoginUserParameters(
    val email: String,
    val password: String,
)
