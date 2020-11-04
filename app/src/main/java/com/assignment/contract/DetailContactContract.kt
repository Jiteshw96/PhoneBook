package com.assignment.contract

import com.assignment.model.Contact

interface DetailContactContract {

    interface DetailContractViewInterface{
        fun initUI()
        fun updateContact(contact: Contact)
        fun deleteContact(contact: Contact)
        fun showToast(message: String)
        fun closeFragment()
        fun setContactDetails(contact: Contact)

    }

    interface  DetailContractPresenterInterface{
        fun getDeleteContactResponse(contact: Contact)
    }

    interface DetailContractModelInterface{
        interface OnFinishDetailContactListener{
            fun onSuccess(message:String)
            fun onFailure()
        }

        fun deleteContactInFirebase(contact: Contact,onFinishDetailContactListener: OnFinishDetailContactListener)
    }
}