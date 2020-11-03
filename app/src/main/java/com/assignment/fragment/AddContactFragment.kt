package com.assignment.fragment

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.assignment.contract.AddNewContactContract.AddContactViewInterface
import com.assignment.model.Contact
import com.assignment.phonebook.R
import com.assignment.phonebook.activity.LaunchActivity
import com.assignment.phonebook.utils.RandomNumberGenerator
import com.assignment.presenter.AddContactPresenter
import kotlinx.android.synthetic.main.fragment_add_contact.*




/**
 * A simple [Fragment] subclass.
 * Use the [AddContactFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddContactFragment : Fragment(),AddContactViewInterface{

    private val addContactPresenter = AddContactPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolBar()
        setUpUI()
    }



    override fun setUpUI(){
        firstname.hint = setMandatoryHintData(firstname.hint.toString())
        lastname.hint = setMandatoryHintData(lastname.hint.toString())

    }

    override fun setToolBar(){
        (activity as LaunchActivity).setSupportActionBar(toolbar)
        (activity as LaunchActivity).supportActionBar?.setHomeButtonEnabled(true)
        (activity as LaunchActivity).supportActionBar?.title = "Save Contact"
        val toolbar = view?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar?.setNavigationIcon(R.drawable.ic_close_black_24dp)
        toolbar?.setNavigationOnClickListener{
           fragmentManager?.popBackStack()
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_contact_menu, menu)

    }

    override fun createContactObject():Contact {
        return Contact(RandomNumberGenerator.getRandomNumber(),email.text.toString(),firstname.text.toString(),lastname.text.toString(),
            middlename.text.toString(),mobile_number.text.toString() ,notes.text.toString())

    }

    override fun closeFragment() {
        showToast("Contact Saved")
        fragmentManager?.popBackStack()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_add -> {
                addNewContact(createContactObject())
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun addNewContact(contact: Contact) {
        addContactPresenter.getAddContactResponse(contact)
    }

    fun setMandatoryHintData(hintData: String): SpannableStringBuilder? {
        val colored = " *"
        val builder = SpannableStringBuilder()
        builder.append(hintData)
        val start = builder.length
        builder.append(colored)
        val end = builder.length
        builder.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.Red)),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return builder
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment AddContactFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = AddContactFragment()
    }
}
