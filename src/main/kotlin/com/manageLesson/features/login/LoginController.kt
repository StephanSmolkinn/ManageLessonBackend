package com.manageLesson.features.login

import com.manageLesson.cache.InMemory
import com.manageLesson.cache.TokenCache
import com.manageLesson.database.tokens.TokenDTO
import com.manageLesson.database.tokens.Tokens
import com.manageLesson.database.users.Users
import com.manageLesson.features.register.RegisterResponseRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class LoginController(private val call: ApplicationCall) {

    suspend fun login() {
        val receiveLogin = call.receive<LoginReceiveRemote>()

        val userDTO = Users.getUser(receiveLogin.login)

        when(userDTO) {
            null -> {
                call.respond(HttpStatusCode.BadRequest, "User not found")
            }

            else -> {
                if (userDTO.password == receiveLogin.password) {
                    val token = UUID.randomUUID().toString()
                    Tokens.insert(
                        TokenDTO(
                            idToken = UUID.randomUUID().toString(),
                            login = receiveLogin.login,
                            token = token
                        )
                    )

                    call.respond(LoginResponseRemote(token = token))
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Invalid password")
                }
            }
        }
    }

}