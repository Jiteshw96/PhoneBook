package com.assignment.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.assignment.contract.RegisterContract.RegisterContractViewInterface

import com.assignment.phonebook.R
import com.assignment.phonebook.activity.LaunchActivity
import com.assignment.phonebook.utils.FirebaseAuthObject
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_register.*
import com.assignment.phonebook.utils.Constants.CONTACTS_FRAGMENT
import com.assignment.presenter.RegisterPresenter



/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment(),RegisterContractViewInterface {
    val registerPresenter = RegisterPresenter(this)
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuthObject.getFirebaseAuth()

        register_btn.setOnClickListener{

            registerUser(register_username.text.trim().toString(),register_password.text.trim().toString())
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RegisterFragment.
         */
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }

    override fun showToast(message: String) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

    override fun proceedToDashBoard() {
        (activity as LaunchActivity).switchFragment(CONTACTS_FRAGMENT,null)
    }

    override fun registerUser(username: String, password: String) {
        registerPresenter.getRegisterResponse(username,password)
    }
}
