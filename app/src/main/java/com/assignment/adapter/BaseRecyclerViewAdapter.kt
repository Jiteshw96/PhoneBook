package com.assignment.adapter

import android.os.Handler
import android.os.Looper
import android.widget.AdapterView
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.assignment.model.Contact
import java.util.*
import kotlin.collections.ArrayList

abstract class BaseRecyclerViewAdapter<T>:  RecyclerView.Adapter<RecyclerView.ViewHolder>() {

     var list: ArrayList<T>? = ArrayList<T>()
    var listFull: ArrayList<T>? = ArrayList<T>()
    protected var itemClickListener: OnItemClickListener? = null


    fun addItems(items: ArrayList<T>) {
        this.listFull?.clear()
        this.list?.addAll(items)
        this.listFull?.addAll(items)
        reload()
    }
    fun removeItems(items: ArrayList<T>){
        this.list?.removeAll(items
        )
        reload()
    }

    fun clear() {
        this.listFull?.clear()
        this.list?.clear()
        reload()
    }

    fun getItem(position: Int): T? {
        return this.list?.get(position)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    override fun getItemCount(): Int = list!!.size

    private fun reload() {
        Handler(Looper.getMainLooper()).post { notifyDataSetChanged() }
    }


}