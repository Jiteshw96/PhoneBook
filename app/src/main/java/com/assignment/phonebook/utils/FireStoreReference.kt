package com.assignment.phonebook.utils

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object FireStoreReference{
    val db = Firebase.firestore
    val auth = FirebaseAuthObject.getFirebaseAuth()
    const val ALL_USERS = "allUsers"

    fun getFireStoreReference():DocumentReference{
       return db.collection(ALL_USERS).document(auth.currentUser?.uid.toString())
    }
}