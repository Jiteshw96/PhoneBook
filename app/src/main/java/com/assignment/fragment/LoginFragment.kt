package com.assignment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.assignment.phonebook.R
import com.assignment.phonebook.activity.LaunchActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import com.assignment.phonebook.utils.Constants.REGISTER_FRAGMENT
import com.assignment.phonebook.utils.Constants.CONTACTS_FRAGMENT
import com.assignment.phonebook.utils.FirebaseAuthObject


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
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
        auth = FirebaseAuthObject.getFirebaseAuth()

        login_btn.setOnClickListener{

            if(login_username.text.isNullOrEmpty() ||login_password.text.isNullOrEmpty()){
                Toast.makeText(context,"Please enter all the details",Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(login_username.text.toString(),login_password.text.toString())
                    .addOnCompleteListener(activity as LaunchActivity) { task->
                        if (task.isSuccessful){
                            (activity as LaunchActivity).switchFragment(CONTACTS_FRAGMENT,null)
                        }else{
                            Toast.makeText(context,"Login failed, please try again",Toast.LENGTH_SHORT).show()
                        }
                    }
            }


        }
        create_account_btn.setOnClickListener{

            (activity as LaunchActivity).switchFragment(REGISTER_FRAGMENT,null)
        }
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
}
