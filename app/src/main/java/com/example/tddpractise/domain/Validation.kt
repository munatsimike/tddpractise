package com.example.tddpractise.domain

object Validation {

    fun isTextEmpty(text: String): Boolean {
        return text.isNotEmpty()
    }
}