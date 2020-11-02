package com.assignment.fragment

import android.bluetooth.le.AdvertiseData
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.adapter.ContactAdapter
import com.assignment.adapter.OnItemClickListener
import com.assignment.model.Contact
import com.assignment.model.User

import com.assignment.phonebook.R
import com.assignment.phonebook.activity.LaunchActivity
import com.assignment.phonebook.utils.Constants.ADD_CONTACT_FRAGMENT
import com.assignment.phonebook.utils.Constants.DETAIL_FRAGMENT
import com.assignment.phonebook.utils.FirebaseAuthObject
import com.assignment.phonebook.utils.RandomNumberGenerator
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_contacts.*
import java.util.*
import kotlin.collections.ArrayList


/**
 * A simple [Fragment] subclass.
 * Use the [ContactsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContactsFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var contactList:List<Contact>
    private lateinit var contactAdapter:ContactAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var addContact: ExtendedFloatingActionButton
    private lateinit var rootView:View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateComponent()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_contacts, container, false)
      //  init()
        initView()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as LaunchActivity).setSupportActionBar(toolbar)
        (activity as LaunchActivity).supportActionBar?.setTitle("")
        getUserContacts()

    }

    private fun onCreateComponent(){
        contactAdapter = ContactAdapter()
    }

    private fun initView(){
        setUpAdapter()
        initializeRecycler()
    }

    private fun setUpAdapter(){
       /* val list = getUserContacts()
        if(!list.isNullOrEmpty()){
         //   contactAdapter.addItems(list as ArrayList<Contact>)
        }*/
        addContact = rootView.findViewById(R.id.add_contact)
        addContact.setOnClickListener{
            (activity as LaunchActivity).switchFragment(ADD_CONTACT_FRAGMENT,null)
        }
        contactAdapter.setOnItemClickListener(onItemClickListener = object: OnItemClickListener{
            override fun onItemClick(position: Int, view: View?) {
                val bundle = Bundle()
                bundle.putSerializable("contact",contactList?.get(position))
                (activity as LaunchActivity).switchFragment(DETAIL_FRAGMENT,bundle)
            }

        })
    }

    private fun initializeRecycler(){
        recyclerView = rootView.findViewById(R.id.recyclerViewContacts)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(DividerItemDecoration(context,DividerItemDecoration.VERTICAL))
        recyclerView.adapter = contactAdapter
    }


   /* fun init(){
        auth = FirebaseAuthObject.getFirebaseAuth()
        db = Firebase.firestore
        val contact = Contact(RandomNumberGenerator.getRandomNumber(),"jayesh.wadh@","arun","wadhwa","",9029,"")
        val user = User(listOf(contact),"jitesh")
        db.collection("allUsers").document(auth.currentUser?.uid.toString()).set(user)
        Toast.makeText(context,auth.currentUser?.uid.toString(),Toast.LENGTH_SHORT).show()
    }*/

    fun getUserContacts():List<Contact>?{
        var user:User? = User()
        auth = FirebaseAuthObject.getFirebaseAuth()
        db = Firebase.firestore
        val docref = db.collection("allUsers").document(auth.currentUser?.uid.toString())
        docref.get().addOnSuccessListener{
                document->
            user = document.toObject(User::class.java)
            if(!user?.contacts.isNullOrEmpty()){
                contactList = user?.contacts!!
                contactAdapter.addItems( user?.contacts as ArrayList<Contact>)
                contactAdapter.notifyDataSetChanged()
            }
            Log.d(TAG, "DocumentSnapshot data: ${user?.contacts?.sortedBy { it.first_name }}")
        }.addOnFailureListener{
                exception ->
            Log.d(TAG, "get failed with ", exception)
        }
        return user?.contacts
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *

         * @return A new instance of fragment ContactsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = ContactsFragment()
    }
}
