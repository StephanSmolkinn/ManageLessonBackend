package com.manageLesson.security

import io.ktor.server.auth.*

data class UserIdPrincipal(val id: Int) : Principal