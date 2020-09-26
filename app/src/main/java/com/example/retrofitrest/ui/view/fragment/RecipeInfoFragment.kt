package com.example.retrofitrest.ui.view.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.setFragmentResultListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.retrofitrest.R
import com.example.retrofitrest.data.model.Recipe
import com.example.retrofitrest.ui.RecipeViewModel
import com.example.retrofitrest.utils.Constants.TransactData.BUNDLE_KEY
import com.example.retrofitrest.utils.Constants.TransactData.REQUEST_KEY
import kotlinx.android.synthetic.main.fragment_recipe_info.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeInfoFragment : BaseFragment() {

    companion object {
        private val LOG_TAG: String = RecipeInfoFragment::class.java.simpleName
    }

    private var mRecipe: Recipe? = null
    private val mViewModelRecipe by viewModel<RecipeViewModel>()
    private lateinit var mMenuFavorite: MenuItem

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
        setFragmentResultListener(REQUEST_KEY) { key, bundle ->
                mRecipe = bundle.getParcelable(BUNDLE_KEY)
                initView(mRecipe!!)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_recipe_item, menu)
        mMenuFavorite = menu.findItem(R.id.menu_recipe_favorite)
        if (mRecipe?.favorite!!) {
            mMenuFavorite.setIcon(R.drawable.ic_favorite)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_recipe_favorite -> {
                if (mRecipe?.favorite!!) {
                    mMenuFavorite.setIcon(R.drawable.ic_unfavorite)
                    mRecipe!!.favorite = false
                } else {
                    mMenuFavorite.setIcon(R.drawable.ic_favorite)
                    mRecipe!!.favorite = true
                }
            }
        }
        mRecipe?.let { mViewModelRecipe.updateRecipe(it) }
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

}