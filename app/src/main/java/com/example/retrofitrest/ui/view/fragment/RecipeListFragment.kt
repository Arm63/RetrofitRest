package com.example.retrofitrest.ui.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitrest.R
import com.example.retrofitrest.data.model.Recipe
import com.example.retrofitrest.ui.RecipeViewModel
import com.example.retrofitrest.ui.adapter.RecipeAdapter
import com.example.retrofitrest.utils.Constants.TransactData.BUNDLE_KEY
import com.example.retrofitrest.utils.Constants.TransactData.REQUEST_KEY
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
    private val recipeInfoFragment by inject<RecipeInfoFragment>()




    // ===========================================================
    // Override Methods or Methods for/from SuperClass
    // ===========================================================

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipe_list, container, false)

        initViews(view)
        setListeners()
        loadData()

        return view
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
            Log.d("asdasdad", "     $it")
            myAdapter.setData(it)
        })
    }

    private fun initViews(view: View) {
        view.rv_fragment_recipelist.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = myAdapter
        }
    }

    private fun setListeners() {
        myAdapter.setOnItemClickListener(object : RecipeAdapter.OnItemClickListener {
            override fun onItemLongClick(item: Recipe, position: Int) {
                deleteItem(item, position)
            }

            override fun onItemClick(item: Recipe, position: Int) {
                setFragmentResult(REQUEST_KEY, bundleOf(BUNDLE_KEY to item))
                openScreen(
                    recipeInfoFragment,
                    true
                )
            }
        })
    }


    private fun deleteItem(item: Recipe, position: Int) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setMessage(R.string.msg_dialog_delete_recipe)
            .setCancelable(false)
            .setPositiveButton(R.string.text_btn_dialog_yes) { dialog, _ ->
                mViewModelRecipe.deleteRecipe(item)
                myAdapter.notifyItemRemoved(position)
                dialog.cancel()
            }
            .setNegativeButton(
                R.string.text_btn_dialog_no
            ) { dialog, _ -> dialog.dismiss() }
        val dialog = builder.create()
        dialog.show()
    }


}

