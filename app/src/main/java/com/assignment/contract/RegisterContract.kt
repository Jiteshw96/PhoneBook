package com.assignment.contract

interface RegisterContract {
    interface  RegisterContractViewInterface{
        fun showToast(message:String)
        fun proceedToDashBoard()
        fun registerUser(username: String,password: String)

    }

    interface  RegisterContractPresenterInterface{
        fun getRegisterResponse(username:String,password:String)
        fun validateInputDetails(username:String,password:String):Boolean
    }

    interface RegisterContractModelInterface{
        interface OnFinishRegisterListener{
            fun onSuccess()
            fun onFailure()
        }
        fun registerUserInDatabase(username:String,password:String, onFinishRegisterListener: OnFinishRegisterListener)
    }
}