package com.assignment.phonebook.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.assignment.contract.LaunchActivityContract
import com.assignment.fragment.*
import com.assignment.phonebook.R
import com.assignment.phonebook.utils.Constants.ADD_CONTACT_FRAGMENT
import com.google.firebase.auth.FirebaseAuth
import com.assignment.phonebook.utils.Constants.LOGIN_FRAGMENT
import com.assignment.phonebook.utils.Constants.REGISTER_FRAGMENT
import com.assignment.phonebook.utils.Constants.CONTACTS_FRAGMENT
import com.assignment.phonebook.utils.Constants.DETAIL_CONTACT_FRAGMENT
import com.assignment.phonebook.utils.Constants.EDIT_FRAGMENT
import com.assignment.phonebook.utils.FirebaseAuthObject

class LaunchActivity : AppCompatActivity(),LaunchActivityContract {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        auth = FirebaseAuthObject.getFirebaseAuth()

        /*   val db = Firebase.firestore
           db.collection("allUsers").document(auth.currentUser?.uid.toString()).get()
           val contact = Contact(RandomNumberGenerator.getRandomNumber(),"jayesh.wadh@","girish","wadhwa","",9029,"")
           val user = User(listOf(contact),"jitesh")

           db.collection("allUsers").document(auth.currentUser?.uid.toString()).set(user)
           db.collection("allUsers").document(auth.currentUser?.uid.toString()).update("contacts",
               FieldValue.arrayUnion(contact)
           ) .addOnCompleteListener{ task->
               if(task.isSuccessful){

               }else{
                   task.exception
               }
           }
   */
        switchFragment(LOGIN_FRAGMENT, null)
        /* signIn.setOnClickListener{

             auth.createUserWithEmailAndPassword(login_password.text.toString(),password.text.toString())
                 .addOnCompleteListener(this){task->
                     if(task.isSuccessful){
                         Toast.makeText(this,"Account Created",Toast.LENGTH_SHORT).show()
                     }else{
                         Toast.makeText(this,"Account Create Failed",Toast.LENGTH_SHORT).show()
                     }
                 }
         }

         login_btn.setOnClickListener {
             auth.signInWithEmailAndPassword(login_password.text.toString(),password.text.toString())
                 .addOnCompleteListener(this) {task->
                     if (task.isSuccessful){

                         Toast.makeText(this,auth.currentUser?.uid.toString(),Toast.LENGTH_SHORT).show()
                     }else{
                         Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show()
                     }
                 }
         }
 */


        //val userProfile:UserProfileChangeRequest = UserProfileChangeRequest.Builder().setDisplayName("NickName").build()


    }


    override fun switchFragment(tag: String, bundle: Bundle?) {
        when (tag) {
            LOGIN_FRAGMENT -> {
                val loginFragment = LoginFragment.newInstance()
                if (bundle != null) {
                    loginFragment.arguments = bundle
                }
                commitFragment(loginFragment, tag)
            }
            REGISTER_FRAGMENT -> {
                val resgiterFragment = RegisterFragment.newInstance()
                if (bundle != null) {
                    resgiterFragment.arguments = bundle
                }
                commitFragment(resgiterFragment, tag)
            }

            CONTACTS_FRAGMENT -> {
                val contactsFragment = ContactsFragment.newInstance()
                if (bundle != null) {
                    contactsFragment.arguments = bundle
                }
                commitFragment(contactsFragment, tag)
            }
            EDIT_FRAGMENT -> {
                val detailFragment = EditFragment.newInstance()
                if (bundle != null) {
                    detailFragment.arguments = bundle
                }
                commitFragment(detailFragment, tag)
            }
            ADD_CONTACT_FRAGMENT -> {
                val addFragment = AddContactFragment.newInstance()
                if (bundle != null) {
                    addFragment.arguments = bundle
                }
                commitFragment(addFragment, tag)
            }
            DETAIL_CONTACT_FRAGMENT->{
                val detailFragment = DetailFragment.newInstance()
                if (bundle != null) {
                    detailFragment.arguments = bundle
                }
                commitFragment(detailFragment, tag)
            }


        }
    }

  override  fun commitFragment(fragment: Fragment, tag: String?) {

        val transition = supportFragmentManager.beginTransaction()
        when (tag) {
            ADD_CONTACT_FRAGMENT -> {
                transition.replace(R.id.fmContainer, fragment, tag).addToBackStack(CONTACTS_FRAGMENT).commit()
            }
            REGISTER_FRAGMENT -> {
                transition.replace(R.id.fmContainer, fragment, tag).addToBackStack(LOGIN_FRAGMENT).commit()
            }
            EDIT_FRAGMENT->{
                transition.replace(R.id.fmContainer, fragment, tag).addToBackStack(CONTACTS_FRAGMENT).commit()
            }
            DETAIL_CONTACT_FRAGMENT->{
                transition.replace(R.id.fmContainer, fragment, tag).addToBackStack(CONTACTS_FRAGMENT).commit()
            }

            else -> {
                transition.replace(R.id.fmContainer, fragment, tag).commit()
            }


        }


    }

    override fun showToast(message: String) {
        TODO("Not yet implemented")
    }

    override fun showProgressBar() {
        TODO("Not yet implemented")
    }

    override fun hideProgressBar() {
        TODO("Not yet implemented")
    }


}
