package com.manageLesson.repository

import com.manageLesson.service.CreateUserParameters
import com.manageLesson.service.LoginUserParameters
import com.manageLesson.utils.BaseResponse

interface UserRepository {
    suspend fun registerUser(parameters: CreateUserParameters): BaseResponse<Any>
    suspend fun login(parameters: LoginUserParameters): BaseResponse<Any>
}