package com.manageLesson.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Users : Table("users") {
    private val login = Users.varchar("login", 25)
    private val password = Users.varchar("password", 25)
    private val name = Users.varchar("name", 25)
    private val email = Users.varchar("email", 25)

    fun insert(userDTO: UserDTO) {
        transaction {
            Users.insert {
                it[login] = userDTO.login
                it[password] = userDTO.password
                it[name] = userDTO.name
                it[email] = userDTO.email ?: ""
            }
        }
    }

    fun getUser(login: String): UserDTO? {
        return try {
            transaction {
                val userModel = Users.select { Users.login.eq(login) }.single()
                UserDTO(
                    login = userModel[Users.login],
                    password = userModel[password],
                    email = userModel[email],
                    name = userModel[name],
                )
            }
        } catch (e: Exception) {
            null
        }
    }
}