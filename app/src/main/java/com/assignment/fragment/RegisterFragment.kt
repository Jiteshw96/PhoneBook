package com.assignment.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.assignment.phonebook.R
import com.assignment.phonebook.activity.LaunchActivity
import com.assignment.phonebook.utils.FirebaseAuthObject
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_register.*
import com.assignment.phonebook.utils.Constants.CONTACTS_FRAGMENT
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
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
            if(register_username.text.isNullOrEmpty() ||register_password.text.isNullOrEmpty()){
                Toast.makeText(context,"Please enter all the details",Toast.LENGTH_SHORT).show()
            }else{
                auth.createUserWithEmailAndPassword(register_username.text.toString(),register_password.text.toString())
                    .addOnCompleteListener(activity as LaunchActivity){ task->
                        if(task.isSuccessful){
                            (activity as LaunchActivity).switchFragment(CONTACTS_FRAGMENT,null)
                        }else{
                            Log.w("Register Exception",task.exception)
                            Toast.makeText(context,"Some error occurred,please try again",Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = RegisterFragment()
    }
}
