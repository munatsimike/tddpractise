package com.example.tddpractise

import com.example.tddpractise.domain.Validation
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidationTest {

    private lateinit var str: String

    @Before
    fun setUp(){
        str = "Michael"
    }

    @Test
    fun whenTextIsEmpty() {
        val text = ""
        val result = Validation.isTextEmpty(text)
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun whenTextIsNotEmpty() {
        val result = Validation.isTextEmpty(str)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenTextIsAllLetters(){
      val result = Validation.textContainsLettersOnly(str)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenTextIsNotAllLetters(){
        val str = "michael7"
        val result = Validation.textContainsLettersOnly(str)
        assertThat(result).isEqualTo(false)
    }
}