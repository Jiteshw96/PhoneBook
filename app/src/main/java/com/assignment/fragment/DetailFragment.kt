package com.assignment.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.assignment.model.Contact

import com.assignment.phonebook.R
import com.assignment.phonebook.activity.LaunchActivity
import kotlinx.android.synthetic.main.fragment_detail.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {
   private lateinit var toggleMenu: Menu
   private lateinit var contact:Contact

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }

    private fun setUpUI(){
        arguments?.getSerializable("contact")?.let {
             contact = it as Contact
        }

        editContact(false)
        setContactDetails(contact)
        setToolBar()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.detail_fragment_menu, menu)
        toggleMenu = menu
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_edit -> {
                editContact(true)
                toggleMenu(true)
            }
            R.id.action_save -> {
                //toggleMenu(false).
                Toast.makeText(context,"Contact Saved",Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        toggleMenu(false)
        super.onPrepareOptionsMenu(menu)
    }


    fun toggleMenu(flag:Boolean){
        val itemSave:MenuItem = toggleMenu.findItem(R.id.action_save)
        val itemEdit:MenuItem = toggleMenu.findItem(R.id.action_edit)
        if (flag){
            itemSave.isVisible = true
            itemEdit.isVisible = false
        }else{
            itemSave.isVisible = false
            itemEdit.isVisible = true
        }

    }


     fun setContactDetails(contact: Contact){
         firstname.setText(contact.first_name)
         lastname.setText(contact.last_name)
         middlename.setText(contact.middle_name)
         email.setText(contact.email)
         contact.mobile_number.let {
             mobile_number.setText(contact.mobile_number.toString())
         }
         notes.setText(contact.notes)

     }

    private fun editContact(editable: Boolean){
        firstname.isEnabled = editable
        middlename.isEnabled = editable
        lastname.isEnabled = editable
        email.isEnabled = editable
        mobile_number.isEnabled = editable
        landlinenumber.isEnabled = editable
        notes.isEnabled = editable
    }

    fun setToolBar(){
        (activity as LaunchActivity).setSupportActionBar(toolbar)
        (activity as LaunchActivity).supportActionBar?.setHomeButtonEnabled(true)
        (activity as LaunchActivity).supportActionBar?.title = "Contact"
        val toolbar = view?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar?.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         *
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = DetailFragment()
    }
}
