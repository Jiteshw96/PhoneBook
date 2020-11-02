package com.assignment.presenter

import com.assignment.contract.AddNewContactContract.AddContactPresenterInterface
import com.assignment.contract.AddNewContactContract.AddContactViewInterface
import com.assignment.model.Contact
import com.assignment.contract.AddNewContactContract.AddContactModelInteractor.OnFinishAddContactListener
import com.assignment.interactor.AddContactInteractor

class AddContactPresenter(addContactViewInterface: AddContactViewInterface) :
    AddContactPresenterInterface, OnFinishAddContactListener {
    private val addContactViewInstance: AddContactViewInterface = addContactViewInterface
    private val addContactModelInteractor= AddContactInteractor()

    override fun validateInputDetails(contact: Contact) {
        if(contact.first_name.trim().isNullOrEmpty() || contact.last_name.trim().isNullOrEmpty()){
            addContactViewInstance.showToast("Please enter all required fields")
        }else{
            getAddContactResponse(contact)
        }

    }

    override fun getAddContactResponse(contact: Contact) {
        addContactModelInteractor.addNewContactInFirebase(contact,this)
    }

    override fun onSuccess() {
        addContactViewInstance.showToast("Contact Saved")
    }

    override fun onFailure() {
        addContactViewInstance.showToast("Some error Occurred, please try again")
    }

}