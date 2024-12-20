package com.manageLesson.features.register

import com.manageLesson.cache.InMemory
import com.manageLesson.cache.TokenCache
import com.manageLesson.features.login.LoginReceiveRemote
import com.manageLesson.features.login.LoginResponseRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureRegisterRouting() {
    routing {
        post("/register") {
            val registerController = RegisterController(call)
            registerController.registerUser()
        }
    }
}