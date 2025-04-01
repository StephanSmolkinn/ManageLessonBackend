package com.manageLesson.di

import com.manageLesson.database.DatabaseFactory
import com.manageLesson.repository.UserRepository
import com.manageLesson.repository.UserRepositoryImpl
import com.manageLesson.service.UserService
import com.manageLesson.service.UserServiceImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val module = module {
    singleOf(::UserServiceImpl).bind<UserService>()
    singleOf(::UserRepositoryImpl).bind<UserRepository>()
}