package com.manageLesson.routes

import com.manageLesson.repository.UserRepository
import com.manageLesson.service.CreateUserParameters
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.authRoutes(userRepository: UserRepository = inject<UserRepository>().value) {
    routing {
        route("/auth") {
            post("/register") {
                val params = call.receive<CreateUserParameters>()
                val result = userRepository.registerUser(params)
                call.respond(result.data ?: "Unknown error")
            }
        }
    }
}