package com.example.retrofitrest.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.retrofitrest.R
import com.example.retrofitrest.utils.FragmentTransactionManager

abstract class BaseFragment : Fragment() {

    // ===========================================================
    // Constants
    // ===========================================================
    // ===========================================================
    // Fields
    // ===========================================================
    // ===========================================================
    // Constructors
    // ===========================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================
    // ===========================================================
    // Methods for/from SuperClass
    // ===========================================================
    // ===========================================================
    // Listeners, methods for/from Interfaces
    // ===========================================================
    // ===========================================================
    // Methods
    // ===========================================================

    fun openScreen(fragment: Fragment, addToBackStack: Boolean) {
        FragmentTransactionManager.displayFragment(
            parentFragmentManager,
            fragment,
            R.id.fl_main_container,
            addToBackStack
        )
    }

    open fun onBackPressed(): Boolean {
        return false
    }


}