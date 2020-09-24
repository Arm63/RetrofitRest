package com.example.retrofitrest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitrest.R
import com.example.retrofitrest.data.model.Recipe
import com.example.retrofitrest.ui.adapter.RecipeAdapter.RecipeHolder
import kotlinx.android.synthetic.main.row_recipe_list_item.view.*


class RecipeAdapter : RecyclerView.Adapter<RecipeHolder>() {
    private var recipeList = emptyList<Recipe>()
    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        return RecipeHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_recipe_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }


    //---------------------------------------------------------------

    inner class RecipeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Recipe) {

            Glide.with(itemView.context)
                .load(item.image)
                .into(itemView.iv_recipe_item)

            itemView.tv_recipe_item_name.text = item.name
            itemView.tv_recipe_item_price.text = item.price.toString()
            itemView.ll_recipe_item_container.setOnClickListener {
                onItemClickListener?.onItemClick(item, adapterPosition)
            }
        }

    }

    fun setData(newList: List<Recipe>) {
        recipeList = newList
        notifyDataSetChanged()
    }


    interface OnItemClickListener {
        fun onItemClick(item: Recipe, position: Int)
    }
}
