package com.assignment.phonebook.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.assignment.fragment.*
import com.assignment.model.Contact
import com.assignment.model.User
import com.assignment.phonebook.R
import com.assignment.phonebook.utils.Constants.ADD_CONTACT_FRAGMENT
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_in.*
import com.assignment.phonebook.utils.Constants.LOGIN_FRAGMENT
import com.assignment.phonebook.utils.Constants.REGISTER_FRAGMENT
import com.assignment.phonebook.utils.Constants.CONTACTS_FRAGMENT
import com.assignment.phonebook.utils.Constants.DETAIL_FRAGMENT
import com.assignment.phonebook.utils.FirebaseAuthObject
import com.assignment.phonebook.utils.RandomNumberGenerator
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore

class LaunchActivity : AppCompatActivity() {

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


    fun switchFragment(tag: String, bundle: Bundle?) {
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
            DETAIL_FRAGMENT -> {
                val detailFragment = DetailFragment.newInstance()
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


        }
    }

    fun commitFragment(fragment: Fragment, tag: String?) {

       /* val fragmentOld = supportFragmentManager.findFragmentByTag(LOGIN_FRAGMENT)
        if (fragmentOld != null) {
            supportFragmentManager.beginTransaction().remove(fragment).commit();
        }*/
        if(tag!!.equals(ADD_CONTACT_FRAGMENT)){
            val transition = supportFragmentManager.beginTransaction()
            transition.replace(R.id.fmContainer, fragment, tag).addToBackStack(CONTACTS_FRAGMENT).commit()
        }
        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.fmContainer, fragment, tag).commit()

    }


}
