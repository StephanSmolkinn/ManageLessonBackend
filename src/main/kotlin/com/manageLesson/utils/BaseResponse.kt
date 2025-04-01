package com.manageLesson.utils

import io.ktor.http.*
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
sealed class BaseResponse<T>(val data: T?, val message: String?) {
    class SuccessResponse<T>(
        data: T? = null,
        message: String? = null,
    ) : BaseResponse<T>(data, message)

    class ErrorResponse<T>(
        data: T? = null,
        message: String? = null,
    ) : BaseResponse<T>(data, message)
}