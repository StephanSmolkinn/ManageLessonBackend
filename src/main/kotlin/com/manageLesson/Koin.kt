package com.manageLesson

import com.manageLesson.di.module
import io.ktor.server.application.*
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(module)
    }
}