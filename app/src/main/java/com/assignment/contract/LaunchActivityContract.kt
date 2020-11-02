package com.assignment.contract

import android.os.Bundle
import androidx.fragment.app.Fragment

interface LaunchActivityContract {


    fun switchFragment(tag: String, bundle: Bundle?)
    fun commitFragment(fragment: Fragment, tag: String?)
    fun showToast(message: String)
    fun showProgressBar()
    fun hideProgressBar()
}