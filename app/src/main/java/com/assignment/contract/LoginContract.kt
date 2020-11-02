package com.assignment.contract

interface  LoginContract{

    interface  LoginViewInterface{
        fun validateUser(username:String,password:String)

        fun checkNetworkAvailable():Boolean
    }

    interface  LoginPresenterInterface{
        fun getLoginResponse(username:String,password:String)
        fun validateInputDetails(username:String,password:String)
    }

    interface LoginModelInterface{
        fun authenticateUser(username:String,password:String)
    }

}