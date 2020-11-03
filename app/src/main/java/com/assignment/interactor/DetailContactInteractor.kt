package com.assignment.interactor

import com.assignment.contract.EditContract.DetailContractModelInterface
import com.assignment.model.Contact
import com.assignment.contract.EditContract.DetailContractModelInterface.OnFinishDetailContactListener
import com.assignment.phonebook.utils.FireStoreReference
import com.google.firebase.firestore.DocumentReference

class DetailContactInteractor : DetailContractModelInterface {
    val document: DocumentReference = FireStoreReference.getFireStoreReference()
    override fun updateContactInFirebase(
        contact: Contact,
        onFinishDetailContactListener: OnFinishDetailContactListener
    ) {

        document.collection("contacts").document("C-${contact.id}").set(contact)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onFinishDetailContactListener.onSuccess("Contact Updated")
                } else {
                    onFinishDetailContactListener.onFailure()
                }
            }
    }

    override fun deleteContactInFirebase(
        contact: Contact,
        onFinishDetailContactListener: OnFinishDetailContactListener
    ) {

        document.collection("contacts").document("C-${contact.id}").delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onFinishDetailContactListener.onSuccess("Contact Deleted")
                } else {
                    onFinishDetailContactListener.onFailure()
                }
            }
    }

}