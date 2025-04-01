package com.manageLesson.service

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserParameters(
    val fullName: String,
    val email: String,
    val password: String,
)
