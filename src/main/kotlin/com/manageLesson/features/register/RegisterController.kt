package com.manageLesson.features.register

import com.manageLesson.database.tokens.TokenDTO
import com.manageLesson.database.tokens.Tokens
import com.manageLesson.database.users.UserDTO
import com.manageLesson.database.users.Users
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.util.*

class RegisterController(private val call: ApplicationCall) {

    suspend fun registerUser() {
        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()

        val userDTO = Users.getUser(registerReceiveRemote.login)

        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User exist")
        } else {
            val token = UUID.randomUUID().toString()
            try {
                Users.insert(
                    UserDTO(
                        login = registerReceiveRemote.login,
                        password = registerReceiveRemote.password,
                        email = registerReceiveRemote.email,
                        name = "",
                    )
                )
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User exist")
            }

            Tokens.insert(
                TokenDTO(
                    idToken = UUID.randomUUID().toString(),
                    login = registerReceiveRemote.login,
                    token = token
                )
            )

            call.respond(RegisterResponseRemote(token = token))
        }
    }

}