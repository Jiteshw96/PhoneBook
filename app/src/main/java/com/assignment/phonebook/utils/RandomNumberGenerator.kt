package com.assignment.phonebook.utils

import java.util.*
import kotlin.random.Random.Default.nextInt
object RandomNumberGenerator{

    fun getRandomNumber():Long{
        var random = (0..99999).random()
        return (System.currentTimeMillis().toString()+random).toLong()
    }


}