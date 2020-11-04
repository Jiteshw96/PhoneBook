package com.assignment.presenter

import com.assignment.contract.RegisterContract.RegisterContractViewInterface
import com.assignment.contract.RegisterContract.RegisterContractPresenterInterface
import com.assignment.interactor.RegisterInteractor
import com.assignment.contract.RegisterContract.RegisterContractModelInterface.OnFinishRegisterListener

class RegisterPresenter(registerContractViewInterface: RegisterContractViewInterface): RegisterContractPresenterInterface,OnFinishRegisterListener{

    val registerContractViewInterface = registerContractViewInterface
    val registerInteractor = RegisterInteractor()
    override fun getRegisterResponse(username: String, password: String) {
        if(validateInputDetails(username,password)){
            registerInteractor.registerUserInDatabase(username,password,this)
        }
    }

    override fun validateInputDetails(username: String, password: String):Boolean {
        if(username.isNullOrEmpty() ||password.isNullOrEmpty()){
           registerContractViewInterface.showToast("Please enter all the details")
            return false
        }

        return  true
    }

    override fun onSuccess() {
        registerContractViewInterface.proceedToDashBoard()
    }

    override fun onFailure() {
        registerContractViewInterface.showToast("Some error occurred,please try again")
    }


}