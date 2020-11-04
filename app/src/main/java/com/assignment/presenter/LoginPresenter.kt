package com.assignment.presenter

import com.assignment.contract.LoginContract
import com.assignment.contract.LoginContract.LoginViewInterface
import com.assignment.contract.LoginContract.LoginPresenterInterface
import com.assignment.contract.LoginContract.LoginModelInterface.OnFinishLoginListener
import com.assignment.interactor.LoginInteractor

class LoginPresenter(loginViewInstance: LoginViewInterface):LoginPresenterInterface,OnFinishLoginListener {
    val loginViewInstance = loginViewInstance
    val loginInteractor  = LoginInteractor()
    override fun getLoginResponse(username: String, password: String) {
        if(validateInputDetails(username,password)){
            loginInteractor.authenticateUser(username,password,this)
        }

    }

    override fun validateInputDetails(username: String, password: String):Boolean {
        if(username.isNullOrEmpty() ||password.isNullOrEmpty()){
            loginViewInstance.showToast("Please enter all the details")
            return false
        }
        return true
    }

    override fun onSuccess() {
        loginViewInstance.proceedToDashBoard()
    }

    override fun onFailure() {
        loginViewInstance.showToast("Login failed, please try again")
    }
}