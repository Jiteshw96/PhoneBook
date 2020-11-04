package com.assignment.presenter

import com.assignment.contract.DetailContactContract.DetailContractPresenterInterface
import com.assignment.contract.DetailContactContract.DetailContractViewInterface
import com.assignment.contract.DetailContactContract.DetailContractModelInterface.OnFinishDetailContactListener
import com.assignment.interactor.DetailContactInteractor
import com.assignment.model.Contact

class DetailContactPresenter(detailContactViewInstance:DetailContractViewInterface): DetailContractPresenterInterface, OnFinishDetailContactListener{
    private val detailContactViewInstance= detailContactViewInstance
    private val detailContactModelInteractor = DetailContactInteractor()
    override fun getDeleteContactResponse(contact: Contact) {
        detailContactModelInteractor.deleteContactInFirebase(contact,this)
    }

    override fun onSuccess(message: String) {
        detailContactViewInstance.showToast(message)
        detailContactViewInstance.closeFragment()

    }

    override fun onFailure() {
        detailContactViewInstance.showToast("Something went wrong,Please try again")
    }

}