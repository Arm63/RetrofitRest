package com.example.retrofitrest.ui.view.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.retrofitrest.R
import com.example.retrofitrest.ui.view.fragment.RecipeListFragment
import com.example.retrofitrest.utils.FragmentTransactionManager
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {


    // ===========================================================
    // Constants
    // ===========================================================

    private val LOG_TAG: String? = MainActivity::class.java.simpleName

    // ===========================================================
    // Fields
    // ===========================================================

    private val instance by inject<RecipeListFragment>()
    private val instance1 by inject<RecipeListFragment>()
    private val instance2 by inject<RecipeListFragment>()


    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Override Methods or Methods for/from SuperClass
    // ===========================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setListeners()
        customizeActionBar()
        initDrawer()
        openScreen(
            instance,
            R.id.nav_recipe_list,
            false
        )
    }

    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun lockDrawer() {
        backButton(0x00000000)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(false)
        dl_main.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun unlockDrawer() {
        backButton(0x00000008)
        showActionBarIcon()
        dl_main.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    private fun customizeActionBar() {
        setActionBarTitle(getString(R.string.app_name))
    }

    // ===========================================================
    // Listeners, methods for/from Interfaces
    // ===========================================================

    private fun setListeners() {
        nav_main.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_recipe_list -> {
                if (!instance.isVisible) {
                    openScreen(
                        instance,
                        R.id.nav_recipe_list,
                        false
                    )
                    Log.d("instance", instance.toString())
                }
            }
        }
        dl_main.closeDrawer(GravityCompat.START)
        return true
    }


    // ===========================================================
    // Methods
    // ===========================================================

    private fun initDrawer() {
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            dl_main,
            mToolbar,
            R.string.msg_navigation_drawer_open,
            R.string.msg_navigation_drawer_close
        )
        dl_main.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    private fun openScreen(fragment: Fragment, item: Int, addToBackStack: Boolean) {
        nav_main.menu.findItem(item).isChecked = true
        FragmentTransactionManager.displayFragment(
            supportFragmentManager,
            fragment,
            R.id.fl_main_container,
            addToBackStack
        )
    }

}