package com.assignment.contract

import com.assignment.model.Contact

interface DetailContract {

    interface  DetailContractViewInterface{
        fun updateContact(contact: Contact)
        fun deleteContact(contact: Contact)

    }

    interface  DetailContractPresenterInterface{
        fun getUpdateContactResponse(contact: Contact)
        fun validateInputDetails(username:String,password:String)
        fun getDeleteContactResponse(contact: Contact)
    }

    interface DetailContractModelInterface{
        fun updateContactInFirebase(contact: Contact)
        fun deleteContactInFirebase(contact: Contact)
    }
}