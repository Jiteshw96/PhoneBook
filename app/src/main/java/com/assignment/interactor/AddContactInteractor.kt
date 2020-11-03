package com.assignment.interactor

import android.util.Log
import com.assignment.contract.AddNewContactContract.AddContactModelInteractor
import com.assignment.model.Contact
import com.assignment.phonebook.utils.FireStoreReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.assignment.contract.AddNewContactContract.AddContactModelInteractor.OnFinishAddContactListener


class AddContactInteractor : AddContactModelInteractor {


    override fun addNewContactInFirebase(
        contact: Contact,
        onFinishAddContactListener: OnFinishAddContactListener
    ) {
        val document: DocumentReference = FireStoreReference.getFireStoreReference()
        document.collection("contacts").document("C-${contact.id}").set(contact)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onFinishAddContactListener.onSuccess()
                } else {
                    onFinishAddContactListener.onFailure()
                }
            }
    }
}
