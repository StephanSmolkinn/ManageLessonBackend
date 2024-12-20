package com.manageLesson

import com.manageLesson.features.login.configureLoginRouting
import com.manageLesson.features.register.configureRegisterRouting
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.contentnegotiation.*
import org.jetbrains.exposed.sql.Database

fun main() {

    Database.connect(
        url = "jdbc:postgresql://localhost:5432/manage_lesson",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "smolkin"
    )

    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)

}

fun Application.module() {
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configureSerialization()
}
