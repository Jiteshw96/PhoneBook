package com.assignment.contract

interface  LoginContract{

    interface  LoginViewInterface{
        fun showToast(message:String)
        fun proceedToDashBoard()
        fun validateUser(username: String,password: String)
    }

    interface  LoginPresenterInterface{
        fun getLoginResponse(username:String,password:String)
        fun validateInputDetails(username:String,password:String):Boolean
    }

    interface LoginModelInterface{
        interface OnFinishLoginListener{
            fun onSuccess()
            fun onFailure()
        }
        fun authenticateUser(username:String,password:String,onFinishLoginListener:OnFinishLoginListener)
    }

}