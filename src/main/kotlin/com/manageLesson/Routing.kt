package com.manageLesson

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.awt.SystemColor.text

@Serializable
data class Test(
    val text: String
)

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond(
                listOf(
                    Test(text = "Hello"),
                    Test(text = "World")
                )
            )
        }
    }
}
