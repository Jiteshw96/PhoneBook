package com.assignment.interactor

import android.util.Log
import android.widget.Toast
import com.assignment.contract.RegisterContract.RegisterContractModelInterface
import com.assignment.contract.RegisterContract.RegisterContractModelInterface.OnFinishRegisterListener
import com.assignment.phonebook.activity.LaunchActivity
import com.assignment.phonebook.utils.Constants
import com.assignment.phonebook.utils.FirebaseAuthObject
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterInteractor: RegisterContractModelInterface {
    private lateinit var auth: FirebaseAuth
    override fun registerUserInDatabase(username: String, password: String,onFinishRegisterListener:OnFinishRegisterListener) {
        auth = FirebaseAuthObject.getFirebaseAuth()
        auth.createUserWithEmailAndPassword(username,password)
            .addOnCompleteListener{ task->
                if(task.isSuccessful){
                   onFinishRegisterListener.onSuccess()
                }else{
                    onFinishRegisterListener.onFailure()
                    Log.w("Register Exception",task.exception)

                }
            }
    }
}