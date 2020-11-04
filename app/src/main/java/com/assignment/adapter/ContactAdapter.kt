package com.assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.assignment.model.Contact
import com.assignment.phonebook.R
import kotlinx.android.synthetic.main.contact_item.view.*

class ContactAdapter : BaseRecyclerViewAdapter<Contact>(),Filterable {
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
        private val number:TextView = view.user_number
        init{
            view.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            itemClickListener?.onItemClick(adapterPosition, v)
        }

        fun setUpView(contact: Contact?) {
           //imageView.setImageDrawable(context.resources.getDrawable(R.drawable.ic_launcher_background))
            textView.text = contact?.first_name?.capitalize()
            number.text  = contact?.mobile_number
        }

    }

    override fun getFilter(): Filter {
        return FilterContact;
    }

    private val FilterContact=object:Filter(){
        override fun performFiltering(charSequence: CharSequence): FilterResults {

            val searchText = charSequence.toString().toLowerCase()
            val filterList: ArrayList<Contact>? = ArrayList<Contact>()
            if(searchText.isEmpty()){
                listFull?.let { filterList?.addAll(it) }
            }else{
                for(item in listFull!!){

                    if(item.first_name.toLowerCase().contains(searchText.toLowerCase())||item.mobile_number.toLowerCase().contains(searchText.toLowerCase())||item.land_line.contains(searchText)){
                        filterList?.add(item)
                    }

                }
            }
            val filterResult = FilterResults()
            filterResult.values = filterList
            return  filterResult
        }

        override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
            list?.clear()
            list?.addAll(filterResults.values as Collection<Contact>)
            notifyDataSetChanged()


        }

    }


}