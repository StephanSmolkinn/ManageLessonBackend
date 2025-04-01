package com.manageLesson.security

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity() {

    JwtConfig.init("manage-lesson")

    install(Authentication) {
        jwt {
            verifier(JwtConfig.instance.verifier)

            validate {
                val claim = it.payload.getClaim(JwtConfig.CLAIM).asInt()

                if (claim != null) {
                    UserIdPrincipal(claim)
                } else {
                    null
                }
            }
        }
    }
}