package com.assignment.model

import java.io.Serializable
data class Contact(
    val id : Long? = null,
    val email: String = "",
    val first_name: String  = "",
    val last_name: String = "",
    val middle_name: String = "",
    val mobile_number: Int? = null,
    val notes: String = ""
):Serializable