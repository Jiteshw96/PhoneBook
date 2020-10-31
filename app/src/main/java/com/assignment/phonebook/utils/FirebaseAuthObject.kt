package com.assignment.phonebook.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object FirebaseAuthObject {

    private lateinit var auth: FirebaseAuth

    fun getFirebaseAuth():FirebaseAuth{
      return Firebase.auth
    }
}