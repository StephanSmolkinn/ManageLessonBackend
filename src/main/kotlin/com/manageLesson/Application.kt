package com.manageLesson

import com.manageLesson.database.DatabaseFactory
import com.manageLesson.repository.UserRepository
import com.manageLesson.repository.UserRepositoryImpl
import com.manageLesson.routes.authRoutes
import com.manageLesson.routes.loginRoutes
import com.manageLesson.security.configureSecurity
import com.manageLesson.service.UserService
import com.manageLesson.service.UserServiceImpl
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.koin.core.Koin
import org.koin.ktor.plugin.Koin

fun main() {

    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)

}

fun Application.module() {
    initKoin()
    configureRouting()
    DatabaseFactory.init()
    authRoutes()
    loginRoutes()
    configureSecurity()
    configureSerialization()
}
