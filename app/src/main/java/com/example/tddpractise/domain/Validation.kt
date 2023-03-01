package com.example.tddpractise.domain

object Validation {

    fun isTextEmpty(text: String): Boolean {
        return text.isNotEmpty()
    }

    fun textContainsLettersOnly(text: String): Boolean {
        return text.all { it.isLetter() }
    }
}