package com.assignment.model

import java.io.Serializable
data class User(
    val contacts: List<Contact>? = null,
    val username: String = ""
):Serializable