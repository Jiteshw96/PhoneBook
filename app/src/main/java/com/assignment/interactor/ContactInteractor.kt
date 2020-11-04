package com.assignment.interactor

import com.assignment.contract.ContactFragmentContract
import com.assignment.model.Contact
import com.assignment.phonebook.utils.Constants
import com.assignment.phonebook.utils.FireStoreReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.assignment.contract.ContactFragmentContract.ContactFragmentModelInterface.OnFinishGetContactListener

class ContactInteractor: ContactFragmentContract.ContactFragmentModelInterface{
    val document: DocumentReference = FireStoreReference.getFireStoreReference()
    override fun getUserContactsFromFirebase(onFinishDetailContactListener:OnFinishGetContactListener) {



        document.collection("contacts").addSnapshotListener{ value, error->
            error?.let {
                onFinishDetailContactListener.onFailure("Something went wrong")
            }
            value?.let {
                val contacts =mutableListOf<Contact>()
                for (document in it.iterator()){
                    val contact = document.toObject(Contact::class.java)
                    contacts.add(contact)
                }
                onFinishDetailContactListener.onSuccess(contacts)

            }

        }
    }

}
