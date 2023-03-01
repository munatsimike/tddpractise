package com.example.tddpractise

object Validation {

    fun validateInput(text: String): Boolean {
        return text.isNotEmpty()
    }
}