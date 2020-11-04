package com.assignment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.assignment.contract.DetailContactContract.DetailContractViewInterface
import com.assignment.model.Contact

import com.assignment.phonebook.R
import com.assignment.phonebook.activity.LaunchActivity
import com.assignment.phonebook.utils.Constants.CONTACTS_FRAGMENT
import com.assignment.phonebook.utils.Constants.EDIT_FRAGMENT
import com.assignment.presenter.DetailContactPresenter
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_detail.*


/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment(), DetailContractViewInterface {
    private lateinit var fullName: TextInputLayout
    private lateinit var emailText: TextInputLayout
    private lateinit var phNumber: TextInputLayout
    private lateinit var landlineText: TextInputLayout
    private lateinit var notesText: TextInputLayout
    private lateinit var contact: Contact
    private lateinit var editButton: Button
    private lateinit var deleteButton: Button
    private var detailContactPresenter = DetailContactPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
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
        setUpScreen()
    }

    fun setUpScreen() {
        initUI()
        editContact(false)
        arguments?.getSerializable("contact")?.let {
            contact = it as Contact
        }
        setContactDetails(contact)
    }

    override fun initUI() {
        fullName = full_name_profile
        emailText = email
        notesText = notes
        landlineText = landline
        phNumber = phone_number
        editButton = edit_button
        deleteButton = delete_button

        editButton.setOnClickListener {
            updateContact(contact)
        }

        deleteButton.setOnClickListener {
            deleteContact(contact)
        }

    }


    private fun editContact(editable: Boolean) {
        fullName.isEnabled = editable
        emailText.isEnabled = editable
        emailText.isEnabled = editable
        phNumber.isEnabled = editable
        landlineText.isEnabled = editable
        notesText.isEnabled = editable
    }

    override fun setContactDetails(contact: Contact) {
        fullname_field.setText(contact.first_name + " " + contact.last_name)
        fullName.editText?.setText(contact.first_name + " " + contact.middle_name + " " + contact.last_name)
        emailText.editText?.setText(contact.email)
        notesText.editText?.setText(contact.notes)
        landlineText.editText?.setText(contact.mobile_number)
        phNumber.editText?.setText(contact.mobile_number)
    }

    override fun updateContact(contact: Contact) {
        val bundle = Bundle()
        bundle.putSerializable("contact", contact)
        (activity as LaunchActivity).switchFragment(EDIT_FRAGMENT, bundle)
    }

    override fun deleteContact(contact: Contact) {
        detailContactPresenter.getDeleteContactResponse(contact)
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun closeFragment() {
        fragmentManager?.popBackStack(CONTACTS_FRAGMENT,1)
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
