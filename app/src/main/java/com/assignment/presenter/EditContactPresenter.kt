package com.assignment.presenter

import com.assignment.interactor.DetailContactInteractor
import com.assignment.contract.EditContract.DetailContractPresenterInterface
import com.assignment.model.Contact
import com.assignment.contract.EditContract.DetailContractViewInterface
import com.assignment.contract.EditContract.DetailContractModelInterface.OnFinishDetailContactListener

class EditContactPresenter(detailContactViewInterface:DetailContractViewInterface):DetailContractPresenterInterface,OnFinishDetailContactListener {
    private val detailContactViewInstance = detailContactViewInterface
    private val detailContactModelInteractor = DetailContactInteractor()

    override fun getUpdateContactResponse(contact: Contact) {
        if(validateInputDetails(contact)){
            detailContactModelInteractor.updateContactInFirebase(contact,this)
        }
    }

    override fun validateInputDetails(contact: Contact):Boolean {
        if(contact.first_name.trim().isNullOrEmpty() || contact.last_name.trim().isNullOrEmpty()){
            detailContactViewInstance.showToast("Please enter all required fields")
            return false
        }
        return  true
    }

    override fun getDeleteContactResponse(contact: Contact) {
        detailContactModelInteractor.deleteContactInFirebase(contact,this)
    }

    override fun onSuccess(message:String) {
        detailContactViewInstance.showToast(message)
        detailContactViewInstance.closeFragment()

    }

    override fun onFailure() {
        detailContactViewInstance.showToast("Something went wrong,Please try again")
    }
}