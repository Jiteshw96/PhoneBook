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

    override fun validateInputDetails(contact: Contact):Boolean {
        if(contact.first_name.trim().isNullOrEmpty() || contact.last_name.trim().isNullOrEmpty()){
            addContactViewInstance.showToast("Please enter all required fields")
            return false
        }
        return  true
    }

    override fun getAddContactResponse(contact: Contact) {
        if(validateInputDetails(contact)){
            addContactModelInteractor.addNewContactInFirebase(contact,this)
        }

    }

    override fun onSuccess() {
        addContactViewInstance.closeFragment()
    }

    override fun onFailure() {
        addContactViewInstance.showToast("Some error Occurred, please try again")
    }

}