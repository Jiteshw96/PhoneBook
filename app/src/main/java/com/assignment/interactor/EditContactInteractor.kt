package com.assignment.interactor

import com.assignment.contract.EditContract.EditContractModelInterfac
import com.assignment.model.Contact
import com.assignment.contract.EditContract.EditContractModelInterfac.OnFinishEditContactListener
import com.assignment.phonebook.utils.FireStoreReference
import com.google.firebase.firestore.DocumentReference

class EditContactInteractor : EditContractModelInterfac {
    val document: DocumentReference = FireStoreReference.getFireStoreReference()
    override fun updateContactInFirebase(
        contact: Contact,
        onFinishEditContactListener: OnFinishEditContactListener
    ) {

        document.collection("contacts").document("C-${contact.id}").set(contact)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onFinishEditContactListener.onSuccess("Contact Updated")
                } else {
                    onFinishEditContactListener.onFailure()
                }
            }
    }

    override fun deleteContactInFirebase(
        contact: Contact,
        onFinishEditContactListener: OnFinishEditContactListener
    ) {

        document.collection("contacts").document("C-${contact.id}").delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onFinishEditContactListener.onSuccess("Contact Deleted")
                } else {
                    onFinishEditContactListener.onFailure()
                }
            }
    }

}