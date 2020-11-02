package com.assignment.contract

import com.assignment.model.Contact

interface AddNewContactContract {

    interface  AddContactViewInterface{
        fun addNewContact(contact:Contact)
        fun setToolBar()
    }

    interface  AddContactPresenterInterface{
        fun validateInputDetails(username:String,password:String)
        fun getAddContactResponse(contact: Contact)
    }

    interface AddContactModelInterface{
        interface OnFinishAddContactListener{
            fun onSuccess()
            fun onFailure()
        }
        fun addNewContactInFirebase(contact: Contact)

    }
}