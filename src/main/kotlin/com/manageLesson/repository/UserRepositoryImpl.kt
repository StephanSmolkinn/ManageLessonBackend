package com.manageLesson.repository

import com.manageLesson.security.JwtConfig
import com.manageLesson.service.CreateUserParameters
import com.manageLesson.service.LoginUserParameters
import com.manageLesson.service.UserService
import com.manageLesson.utils.BaseResponse
import com.manageLesson.utils.isValidField

class UserRepositoryImpl(private val service: UserService) : UserRepository {
    override suspend fun registerUser(parameters: CreateUserParameters): BaseResponse<Any> {
        val isValidParameters = isValidField(listOf(parameters.email, parameters.fullName, parameters.password))
        isValidParameters?.let {
            return BaseResponse.ErrorResponse(data = it)
        }

        return if (checkEmailExists(parameters.email)) {
            BaseResponse.ErrorResponse(data = "Email already registered")
        } else {
            val user = service.registerUser(parameters)

            when (user) {
                null -> BaseResponse.ErrorResponse()

                else -> {
                    val token = JwtConfig.instance.createAccessToken(user.id)
                    user.authToken = token
                    BaseResponse.SuccessResponse(data = user, message = "User registered successful")
                }
            }
        }
    }

    override suspend fun login(parameters: LoginUserParameters): BaseResponse<Any> {
        val user = service.loginUser(parameters)

        user?.let {
            val token = JwtConfig.instance.createAccessToken(user.id)
            it.authToken = token
            return BaseResponse.SuccessResponse(data = it)
        }

        return BaseResponse.ErrorResponse(data = "Email or password invalid")
    }

    private suspend fun checkEmailExists(email: String): Boolean {
        return service.findUserByEmail(email) != null
    }
}