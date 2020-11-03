package com.assignment.contract

import com.assignment.model.Contact

interface EditContract {

    interface DetailContractViewInterface{
        fun updateContact(contact: Contact)
        fun deleteContact(contact: Contact)
        fun showToast(message: String)
        fun closeFragment()
        fun createContactObject():Contact

    }

    interface  DetailContractPresenterInterface{
        fun getUpdateContactResponse(contact: Contact)
        fun validateInputDetails(contact: Contact):Boolean
        fun getDeleteContactResponse(contact: Contact)
    }

    interface DetailContractModelInterface{
        interface OnFinishDetailContactListener{
            fun onSuccess(message:String)
            fun onFailure()
        }
        fun updateContactInFirebase(contact: Contact,onFinishDetailContactListener: OnFinishDetailContactListener)
        fun deleteContactInFirebase(contact: Contact,onFinishDetailContactListener: OnFinishDetailContactListener)
    }
}