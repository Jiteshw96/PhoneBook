package com.assignment.interactor

import com.assignment.contract.DetailContactContract.DetailContractModelInterface
import com.assignment.model.Contact
import com.assignment.phonebook.utils.FireStoreReference
import com.google.firebase.firestore.DocumentReference

class DetailContactInteractor: DetailContractModelInterface {
    override fun deleteContactInFirebase(
        contact: Contact,
        onFinishDetailContactListener: DetailContractModelInterface.OnFinishDetailContactListener
    ) {
        val document: DocumentReference = FireStoreReference.getFireStoreReference()
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