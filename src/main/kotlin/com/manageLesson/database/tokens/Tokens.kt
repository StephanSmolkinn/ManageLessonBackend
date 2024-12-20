package com.manageLesson.database.tokens

import com.manageLesson.database.users.UserDTO
import com.manageLesson.database.users.Users
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens : Table("tokens") {
    private val id_token = Tokens.varchar("id_token", 50)
    private val login = Tokens.varchar("login", 25)
    private val token = Tokens.varchar("token", 50)

    fun insert(tokenDTO: TokenDTO) {
        transaction {
            Tokens.insert {
                it[id_token] = tokenDTO.idToken
                it[login] = tokenDTO.login
                it[token] = tokenDTO.token
            }
        }
    }
}