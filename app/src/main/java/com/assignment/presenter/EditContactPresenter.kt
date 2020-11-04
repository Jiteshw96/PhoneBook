package com.assignment.presenter

import com.assignment.interactor.EditContactInteractor
import com.assignment.contract.EditContract.EditContractPresenterInterface
import com.assignment.model.Contact
import com.assignment.contract.EditContract.EditContractViewInterface
import com.assignment.contract.EditContract.EditContractModelInterfac.OnFinishEditContactListener

class EditContactPresenter(editContactViewInterface:EditContractViewInterface):EditContractPresenterInterface,OnFinishEditContactListener {
    private val editContactViewInstance = editContactViewInterface
    private val editContactModelInteractor = EditContactInteractor()

    override fun getUpdateContactResponse(contact: Contact) {
        if(validateInputDetails(contact)){
            editContactModelInteractor.updateContactInFirebase(contact,this)
        }
    }

    override fun validateInputDetails(contact: Contact):Boolean {
        if(contact.first_name.trim().isNullOrEmpty() || contact.last_name.trim().isNullOrEmpty()){
            editContactViewInstance.showToast("Please enter all required fields")
            return false
        }
        return  true
    }

    override fun getDeleteContactResponse(contact: Contact) {
        editContactModelInteractor.deleteContactInFirebase(contact,this)
    }

    override fun onSuccess(message:String) {
        editContactViewInstance.showToast(message)
        editContactViewInstance.closeFragment()

    }

    override fun onFailure() {
        editContactViewInstance.showToast("Something went wrong,Please try again")
    }
}