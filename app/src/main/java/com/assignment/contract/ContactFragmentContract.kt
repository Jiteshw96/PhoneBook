package com.assignment.contract

import com.assignment.model.Contact

interface ContactFragmentContract {

    interface  ContactFragmentViewInterface{
        fun listAllContacts()
        fun sortContacts()
        fun showToast(message:String)
    }

    interface  ContactFragmentPresenterInterface{
        fun getContactList()

    }

    interface ContactFragmentModelInterface{
        fun getUserContactsFromFirebase()
    }


}