package com.assignment.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.assignment.model.Contact

import com.assignment.phonebook.R
import com.assignment.phonebook.activity.LaunchActivity
import kotlinx.android.synthetic.main.fragment_edit.*
import com.assignment.contract.EditContract.DetailContractViewInterface
import com.assignment.presenter.EditContactPresenter


/**
 * A simple [Fragment] subclass.
 * Use the [EditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditFragment : Fragment(), DetailContractViewInterface {
    private lateinit var toggleMenu: Menu
    private lateinit var contact: Contact
    private val addDetailContactPresenter = EditContactPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUI()
    }

    private fun setUpUI() {
        arguments?.getSerializable("contact")?.let {
            contact = it as Contact
        }
        setToolBar()
        editContact(false)
        setContactDetails(contact)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.detail_fragment_menu, menu)
        toggleMenu = menu
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_edit -> {
                editContact(true)
                toggleMenu(true)
            }
            R.id.action_save -> {
                toggleMenu(false)
                editContact(false)
                val contact = createContactObject()
                updateContact(contact)

            }
            R.id.action_delete -> {
                deleteContact(contact)
            }
        }
        return true
    }

    override fun createContactObject(): Contact {

        return Contact(
            contact.id, email.text.toString(), firstname.text.toString(), lastname.text.toString(),
            middlename.text.toString(), mobile_number.text.toString(), notes.text.toString()
        )

    }

    override fun closeFragment() {
        fragmentManager?.popBackStack()

    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        toggleMenu(false)
        super.onPrepareOptionsMenu(menu)
    }


    fun toggleMenu(flag: Boolean) {
        val itemSave: MenuItem = toggleMenu.findItem(R.id.action_save)
        val itemEdit: MenuItem = toggleMenu.findItem(R.id.action_edit)
        if (flag) {
            itemSave.isVisible = true
            itemEdit.isVisible = false
        } else {
            itemSave.isVisible = false
            itemEdit.isVisible = true
        }

    }


    fun setContactDetails(contact: Contact) {
        firstname.setText(contact.first_name)
        lastname.setText(contact.last_name)
        middlename.setText(contact.middle_name)
        email.setText(contact.email)
        mobile_number.setText(contact.mobile_number)
        notes.setText(contact.notes)

    }

    private fun editContact(editable: Boolean) {
        firstname.isEnabled = editable
        middlename.isEnabled = editable
        lastname.isEnabled = editable
        email.isEnabled = editable
        mobile_number.isEnabled = editable
        landlinenumber.isEnabled = editable
        notes.isEnabled = editable
    }

    fun setToolBar() {
        (activity as LaunchActivity).setSupportActionBar(toolbar)
        (activity as LaunchActivity).supportActionBar?.setHomeButtonEnabled(true)
        (activity as LaunchActivity).supportActionBar?.title = "Contact"
        val toolbar = view?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar?.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24dp)
        toolbar?.setNavigationOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         *
         * @return A new instance of fragment DetailFragment.
         */
        @JvmStatic
        fun newInstance() = EditFragment()
    }

    override fun updateContact(contact: Contact) {
        addDetailContactPresenter.getUpdateContactResponse(contact)
    }

    override fun deleteContact(contact: Contact) {
        addDetailContactPresenter.getDeleteContactResponse(contact)
    }
}
