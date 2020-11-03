package com.assignment.contract

import com.assignment.model.Contact

interface AddNewContactContract {

    interface  AddContactViewInterface{
        fun addNewContact(contact:Contact)
        fun setToolBar()
        fun showToast(message:String)
        fun setUpUI()
        fun createContactObject():Contact
        fun closeFragment()
    }

    interface  AddContactPresenterInterface{
       fun validateInputDetails(contact: Contact):Boolean
        fun getAddContactResponse(contact: Contact)
    }

    interface AddContactModelInteractor{
        interface OnFinishAddContactListener{
                fun onSuccess()
                fun onFailure()
        }
        fun addNewContactInFirebase(contact: Contact,onFinishAddContactListener: OnFinishAddContactListener)

    }
}