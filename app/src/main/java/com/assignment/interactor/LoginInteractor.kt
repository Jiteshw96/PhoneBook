package com.assignment.interactor

import android.widget.Toast
import com.assignment.contract.LoginContract.LoginModelInterface
import com.assignment.contract.LoginContract.LoginModelInterface.OnFinishLoginListener
import com.assignment.phonebook.activity.LaunchActivity
import com.assignment.phonebook.utils.Constants
import com.assignment.phonebook.utils.FirebaseAuthObject
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*

class LoginInteractor:LoginModelInterface {
    private lateinit var auth: FirebaseAuth
    override fun authenticateUser(username: String, password: String,onFinishLoginListener:OnFinishLoginListener) {
        auth = FirebaseAuthObject.getFirebaseAuth()
        auth.signInWithEmailAndPassword(username,password)
            .addOnCompleteListener() { task->
                if (task.isSuccessful){
                    onFinishLoginListener.onSuccess()
                }else{
                    onFinishLoginListener.onFailure()
                }
            }
    }

}