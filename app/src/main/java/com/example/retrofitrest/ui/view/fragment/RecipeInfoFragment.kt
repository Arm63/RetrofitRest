package com.example.retrofitrest.ui.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.retrofitrest.R
import com.example.retrofitrest.data.model.Recipe
import com.example.retrofitrest.ui.RecipeViewModel
import com.example.retrofitrest.ui.view.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_recipe_info.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeInfoFragment : BaseFragment() {

    companion object {
        private var INSTANCE: RecipeInfoFragment? = null
        fun getInstance(): RecipeInfoFragment {
            if (INSTANCE == null)
                INSTANCE = RecipeInfoFragment()
            return INSTANCE as RecipeInfoFragment
        }
    }

    private var id: Int? = null
    private val mViewModelRecipe by viewModel<RecipeViewModel>()


    override fun onAttach(context: Context) {
        super.onAttach(context)
//        (activity as MainActivity).lockDrawer()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_info, container, false)

        loadData()
        return view
    }

    private fun loadData() {
        setFragmentResultListener("requestKey") { key, bundle ->
            id = bundle.getInt("bundleKey")
            mViewModelRecipe.getRecipe(id!!).observe(viewLifecycleOwner, {
                initView(it)
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView(recipe: Recipe) {
        Glide.with(requireContext())
            .load(recipe.image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(iv_recipe_image)

        tv_recipe_title.text = recipe.name
        tv_recipe_price.text = recipe.price.toString()
        tv_recipe_description.text = recipe.description
    }

    override fun onDestroy() {
        super.onDestroy()
//        (activity as MainActivity).unlockDrawer()

    }
}