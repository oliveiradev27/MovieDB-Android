package br.espartano.moviedbapp.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {


    fun formatDate(text: String) : String {
        var formatter = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())
        formatter.parse(text)?.let {
            val date: Date = it
            formatter = SimpleDateFormat("dd/mm/yyyy", Locale.getDefault())
            return  formatter.format(date)
        }

        return text
    }
}