package com.assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.assignment.model.Contact
import com.assignment.phonebook.R
import kotlinx.android.synthetic.main.contact_item.view.*

class ContactAdapter : BaseRecyclerViewAdapter<Contact>() {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent?.context
        return ContactViewHolder(
            LayoutInflater.from(parent?.context).inflate(R.layout.contact_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var viewHolder = holder as ContactViewHolder
        val contact = getItem(position)
        viewHolder.setUpView(contact)

    }


    inner class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        private val imageView: ImageView = view.profile_image
        private val textView:TextView = view.user_name
        init{
            view.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            itemClickListener?.onItemClick(adapterPosition, v)
        }

        fun setUpView(contact: Contact?) {
           //imageView.setImageDrawable(context.resources.getDrawable(R.drawable.ic_launcher_background))
            textView.text = contact?.first_name
        }

    }


}