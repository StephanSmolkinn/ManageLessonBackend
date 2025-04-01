package com.manageLesson.routes

import com.manageLesson.repository.UserRepository
import com.manageLesson.service.LoginUserParameters
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.loginRoutes(userRepository: UserRepository = inject<UserRepository>().value) {
    routing {
        route("/auth") {
            post("/login") {
                val params = call.receive<LoginUserParameters>()
                val result = userRepository.login(params)
                call.respond(result.data ?: "no data")
            }
        }
    }
}