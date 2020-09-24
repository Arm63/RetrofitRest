package com.example.retrofitrest.ui.view.fragment

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitrest.R
import com.example.retrofitrest.data.model.Recipe
import com.example.retrofitrest.ui.RecipeViewModel
import com.example.retrofitrest.ui.adapter.RecipeAdapter
import kotlinx.android.synthetic.main.fragment_recipe_list.*
import kotlinx.android.synthetic.main.fragment_recipe_list.view.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeListFragment : BaseFragment() {


    // ===========================================================
    // Statics, Constants
    // ===========================================================

    companion object {
        private val LOG_TAG: String = RecipeListFragment::class.java.simpleName

    }

    // ===========================================================
    // Fields
    // ===========================================================

    private val myAdapter by inject<RecipeAdapter>()
    private val mViewModelRecipe by viewModel<RecipeViewModel>()


    // ===========================================================
    // Override Methods or Methods for/from SuperClass
    // ===========================================================

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("shinvav","")
        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)
        initViews(view)
        loadData()

        return view
    }

    private fun initViews(view: View) {
        view.rv_fragment_recipelist.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = myAdapter
        }
        myAdapter.setOnItemClickListener(object : RecipeAdapter.OnItemClickListener {
            override fun onItemClick(item: Recipe, position: Int) {
                setFragmentResult("requestKey", bundleOf("bundleKey" to item.id))
                openScreen(
                    RecipeInfoFragment.getInstance(),
                    true
                )

            }
        })
    }

    // ===========================================================
    // Methods
    // ===========================================================

    private fun loadData() {
        mViewModelRecipe.isDbEmpty().observe(viewLifecycleOwner, {
            if (it == 0)
                mViewModelRecipe.load()
        })

        mViewModelRecipe.getRecipes().observe(viewLifecycleOwner, {
            myAdapter.setData(it)
        })
    }

}