package com.example.tddpractise

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidationTest {

    @Test
    fun whenTextIsEmpty() {
        val text = ""
        val result = Validation.validateInput(text)
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun whenTextIsNotEmpty() {
        val text = "michael"
        val result = Validation.validateInput(text)
        assertThat(result).isEqualTo(true)
    }
}