package com.lifestrim.topmovies.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter.ofPattern
import java.util.*

class DateConverter {
    fun getDate(stringDate: String): String? {
        val date = LocalDate.parse(stringDate)
        val formatter = ofPattern("MMMM dd, yyyy", Locale.ENGLISH)
        return date.format(formatter).toString()
    }
}