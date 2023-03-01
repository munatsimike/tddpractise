package com.example.tddpractise.domain

object Validation {

    fun validateInput(text: String): Boolean {
        return text.isNotEmpty()
    }
}