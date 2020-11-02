package com.assignment.contract

interface RegisterContract {
    interface  RegisterContractViewInterface{
        fun registerUser(username:String,password:String)
        fun showToast(message:String)
        fun checkNetworkAvailable():Boolean

    }

    interface  RegisterContractPresenterInterface{
        fun getRegisterResponse(username:String,password:String)
        fun validateInputDetails(username:String,password:String)
    }

    interface RegisterContractModelInterface{
        fun registerUserInDatabase(username:String,password:String)
    }
}