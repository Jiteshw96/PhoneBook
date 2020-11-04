package com.assignment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.assignment.contract.LoginContract

import com.assignment.phonebook.R
import com.assignment.phonebook.activity.LaunchActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import com.assignment.phonebook.utils.Constants.REGISTER_FRAGMENT
import com.assignment.phonebook.utils.Constants.CONTACTS_FRAGMENT
import com.assignment.phonebook.utils.FirebaseAuthObject
import com.assignment.contract.LoginContract.LoginViewInterface
import com.assignment.presenter.LoginPresenter

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(), LoginViewInterface {
    val loginPresenter = LoginPresenter(this)
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        login_btn.setOnClickListener{

            validateUser(login_username.text.trim().toString(),login_password.text.trim().toString())

        }
        create_account_btn.setOnClickListener{

            (activity as LaunchActivity).switchFragment(REGISTER_FRAGMENT,null)
        }
    }

    override fun validateUser(username: String, password: String) {
       loginPresenter.getLoginResponse(username,password)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment LoginFragment.
         */
        @JvmStatic
        fun newInstance() = LoginFragment()

    }


    override fun showToast(message: String) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }

    override fun proceedToDashBoard() {
        (activity as LaunchActivity).switchFragment(CONTACTS_FRAGMENT,null)
    }
}
