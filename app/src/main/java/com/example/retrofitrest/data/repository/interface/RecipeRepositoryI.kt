package com.example.retrofitrest.data.repository.`interface`

import androidx.lifecycle.LiveData
import com.example.retrofitrest.data.model.Recipe

interface RecipeRepositoryI {
    suspend fun load()
    suspend fun addRecipes(recipe: List<Recipe>)
    suspend fun updateRecipe(recipe: Recipe)

    fun getRecipes(): LiveData<List<Recipe>>
    fun getRecipe(id: Int): LiveData<Recipe>
    fun isDbEmpty(): LiveData<Int>
}