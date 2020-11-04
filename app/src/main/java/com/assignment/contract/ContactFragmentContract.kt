package com.assignment.contract

import com.assignment.model.Contact

interface ContactFragmentContract {

    interface  ContactFragmentViewInterface{
        fun showToast(message:String)
        fun updateData(contacts: List<Contact>,tag:String)
        fun getUserContacts()
    }

    interface  ContactFragmentPresenterInterface{
        fun getContactList()

    }

    interface ContactFragmentModelInterface{
        interface OnFinishGetContactListener{
            fun onSuccess(contacts:List<Contact>)
            fun onFailure(message: String)
        }
        fun getUserContactsFromFirebase(onFinishDetailContactListener:OnFinishGetContactListener)
    }


}