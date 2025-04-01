package com.manageLesson.security

import io.ktor.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

private const val SECRET_KEY = "6732456546"
private const val ALGORITHM = "HmacSHA1"
private val HASH_KEY = hex(SECRET_KEY)
private val HMAC_KEY = SecretKeySpec(HASH_KEY, ALGORITHM)

fun hash(password: String): String {
    val hmac = Mac.getInstance(ALGORITHM).apply { init(HMAC_KEY) }
    return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
}