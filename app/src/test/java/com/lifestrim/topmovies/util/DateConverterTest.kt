package com.lifestrim.topmovies.util

import org.junit.Test

import org.junit.Assert.*
import java.text.DateFormat
import java.util.*

class DateConverterTest {

    @Test
    fun getDate_isCorrectFormat() {
        val stringDate = "July 30, 2020"

        val stringDateForTest = DateConverter().getDate("2020-07-30")

        assertEquals(stringDate, stringDateForTest)
    }
}