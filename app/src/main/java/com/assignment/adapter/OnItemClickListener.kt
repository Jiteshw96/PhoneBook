package com.assignment.adapter

import android.view.View

interface OnItemClickListener {

    abstract   fun onItemClick(position: Int, view: View?)
}