package com.assignment.presenter

import com.assignment.contract.ContactFragmentContract
import  com.assignment.contract.ContactFragmentContract.ContactFragmentPresenterInterface
import com.assignment.contract.ContactFragmentContract.ContactFragmentViewInterface
import com.assignment.interactor.ContactInteractor
import com.assignment.contract.ContactFragmentContract.ContactFragmentModelInterface.OnFinishGetContactListener
import com.assignment.model.Contact
import com.assignment.phonebook.utils.Constants.DEFAULT

class ContactPresenter(contactFragmentViewInstace:ContactFragmentViewInterface):ContactFragmentPresenterInterface,OnFinishGetContactListener {
   val contactFragmentViewInstance = contactFragmentViewInstace
    val contactFragmentInteractor = ContactInteractor()
    override fun getContactList(){
       contactFragmentInteractor.getUserContactsFromFirebase(this)
    }

    override fun onSuccess(contacts: List<Contact>) {
        contactFragmentViewInstance.updateData(contacts,DEFAULT)
    }

    override fun onFailure(message: String) {
        contactFragmentViewInstance.showToast("Something went wrong")
    }


}