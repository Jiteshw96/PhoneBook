package com.assignment.fragment

import android.os.Bundle
import android.view.*
import android.widget.Filter
import android.widget.Filterable
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.adapter.ContactAdapter
import com.assignment.adapter.OnItemClickListener
import com.assignment.contract.ContactFragmentContract
import com.assignment.model.Contact
import com.assignment.model.User
import com.assignment.phonebook.R
import com.assignment.phonebook.activity.LaunchActivity
import com.assignment.phonebook.utils.Constants.ADD_CONTACT_FRAGMENT
import com.assignment.phonebook.utils.Constants.BY_NAME
import com.assignment.phonebook.utils.Constants.DEFAULT
import com.assignment.phonebook.utils.Constants.DETAIL_CONTACT_FRAGMENT
import com.assignment.phonebook.utils.Constants.EDIT_FRAGMENT
import com.assignment.phonebook.utils.FirebaseAuthObject
import com.assignment.phonebook.utils.RandomNumberGenerator
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_contacts.*
import com.assignment.contract.ContactFragmentContract.ContactFragmentViewInterface
import com.assignment.presenter.ContactPresenter


/**
 * A simple [Fragment] subclass.
 * Use the [ContactsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ContactsFragment : Fragment(), ContactFragmentViewInterface {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private  var contactList= mutableListOf<Contact>()
    private lateinit var contactAdapter:ContactAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var addContact: ExtendedFloatingActionButton
    private lateinit var rootView:View
    private var contactPresenter = ContactPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        onCreateComponent()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_contacts, container, false)
       // init()
        initView()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as LaunchActivity).setSupportActionBar(toolbar)
        (activity as LaunchActivity).supportActionBar?.setTitle("Contacts")
        getUserContacts()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.contacts_menu, menu)
        val menuItem = menu.findItem(R.id.search_action)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(s: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(s: String?): Boolean {
                contactAdapter.filter.filter(s.toString())
                return false
            }

        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.sort_name->{
                updateData(contactList,BY_NAME)
            }
            R.id.search_action->{

            }
        }
        return true
    }


    private fun onCreateComponent(){
        contactAdapter = ContactAdapter()
    }

    private fun initView(){
        setUpAdapter()
        initializeRecycler()
    }

    private fun setUpAdapter(){
        addContact = rootView.findViewById(R.id.add_contact)
        addContact.setOnClickListener{
            (activity as LaunchActivity).switchFragment(ADD_CONTACT_FRAGMENT, null)
        }
        contactAdapter.setOnItemClickListener(onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(position: Int, view: View?) {
                val bundle = Bundle()
                bundle.putSerializable("contact", contactList?.get(position))
                (activity as LaunchActivity).switchFragment(DETAIL_CONTACT_FRAGMENT, bundle)
            }

        })
    }

    private fun initializeRecycler(){
        recyclerView = rootView.findViewById(R.id.recyclerViewContacts)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val divider = DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(
            ContextCompat.getDrawable(
                context!!,
                R.drawable.custom_divider
            )!!
        )
        recyclerView.addItemDecoration(divider)
        recyclerView.adapter = contactAdapter
    }


    override fun getUserContacts(){
        contactAdapter.clear()
        contactPresenter.getContactList()
    }


   override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

   override fun updateData(contacts: List<Contact>,tag:String){
        contactList = contacts as MutableList<Contact>
        when(tag){
            BY_NAME->{
                contactList.sortBy { it.first_name.toUpperCase() }
            }
        }
        if(!contacts.isNullOrEmpty()){
            contactAdapter.clear()
            contactAdapter.addItems(contacts as ArrayList<Contact>)
            contactAdapter.notifyDataSetChanged()
        }
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
