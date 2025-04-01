package com.manageLesson.service

import com.manageLesson.database.DatabaseFactory.dbQuery
import com.manageLesson.database.UserTable
import com.manageLesson.model.User
import com.manageLesson.security.hash
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserServiceImpl : UserService {
    override suspend fun registerUser(parameters: CreateUserParameters): User? {
        var statement: InsertStatement<Number>? = null
        dbQuery {
            statement = UserTable.insert {
                it[fullName] = parameters.fullName
                it[email] = parameters.email
                it[password] = hash(parameters.password)
            }
        }
        return rowToUser(statement?.resultedValues?.get(0))
    }

    override suspend fun findUserByEmail(email: String): User? {
        val user = dbQuery {
            UserTable.select { UserTable.email.eq(email) }.map {
                rowToUser(it)
            }.singleOrNull()
        }

        return user
    }

    override suspend fun loginUser(parameters: LoginUserParameters): User? {
        val user = dbQuery {
            UserTable.select {
                UserTable.email.eq(parameters.email) and UserTable.password.eq(hash(parameters.password))
            }.singleOrNull()
        }

        return user?.let {
            User(
                id = user[UserTable.id],
                fullName = user[UserTable.fullName],
                email = user[UserTable.email],
                createdAt = user[UserTable.createdAt].toString()
            )
        }
    }

    private fun rowToUser(row: ResultRow?): User? {
        return if (row == null) null
            else User(
                id = row[UserTable.id],
                fullName = row[UserTable.fullName],
                email = row[UserTable.email],
                createdAt = row[UserTable.createdAt].toString(),
            )
    }
}