package com.manageLesson

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.awt.SystemColor.text

fun Application.configureRouting() {
    routing {
        get("/") {
        }
    }
}
