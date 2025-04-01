package com.manageLesson.service

import com.manageLesson.model.User
import org.jetbrains.exposed.sql.ResultRow

interface UserService {
    suspend fun registerUser(parameters: CreateUserParameters): User?
    suspend fun findUserByEmail(email: String): User?
    suspend fun loginUser(parameters: LoginUserParameters): User?
}