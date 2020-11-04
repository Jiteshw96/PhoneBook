package com.assignment.contract

import com.assignment.model.Contact

interface EditContract {

    interface EditContractViewInterface{
        fun updateContact(contact: Contact)
        fun deleteContact(contact: Contact)
        fun showToast(message: String)
        fun closeFragment()
        fun createContactObject():Contact

    }

    interface  EditContractPresenterInterface{
        fun getUpdateContactResponse(contact: Contact)
        fun validateInputDetails(contact: Contact):Boolean
        fun getDeleteContactResponse(contact: Contact)
    }

    interface EditContractModelInterfac{
        interface OnFinishEditContactListener{
            fun onSuccess(message:String)
            fun onFailure()
        }
        fun updateContactInFirebase(contact: Contact, onFinishEditContactListener: OnFinishEditContactListener)
        fun deleteContactInFirebase(contact: Contact, onFinishEditContactListener: OnFinishEditContactListener)
    }
}