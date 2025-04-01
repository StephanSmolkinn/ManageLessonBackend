package com.manageLesson.utils

fun isValidField(list: List<String>): String? {
    list.forEach { field ->
        return when {
            field.isEmpty() -> "Field is empty"
            field.length < 5 -> "$field is too short"
            field.length > 25 -> "$field is too long"

            else -> null
        }
    }

    return null
}