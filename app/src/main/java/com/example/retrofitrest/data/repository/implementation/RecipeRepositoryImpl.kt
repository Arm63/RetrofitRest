package com.example.retrofitrest.data.repository.implementation

import com.example.retrofitrest.data.RecipeDatabase
import com.example.retrofitrest.data.api.APIService
import com.example.retrofitrest.data.model.Recipe
import com.example.retrofitrest.data.repository.`interface`.RecipeRepositoryI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RecipeRepositoryImpl(private val database: RecipeDatabase, private val service: APIService) {

    companion object {
        private var INSTANCE: RecipeRepositoryImpl? = null
        fun getInstance(getDatabase: RecipeDatabase, service: APIService): RecipeRepositoryImpl {
            if (INSTANCE == null)
                INSTANCE = RecipeRepositoryImpl(getDatabase, service)
            return INSTANCE as RecipeRepositoryImpl
        }
    }

    suspend fun load() {
        val response = service.getRecipes()
        if (response.isSuccessful)
            response.body()?.let {
                withContext(Dispatchers.Main) {
                    addRecipes(it)
                }
            }
    }

    private suspend fun addRecipes(recipe: List<Recipe>) {
        database.userDao().insertRecipes(recipe)
    }

    fun getRecipes() = database.userDao().getAllData()

    fun getRecipe(id: Int) = database.userDao().getItem(id)

    fun isDbEmpty() = database.userDao().isDbEmpty()


    suspend fun updateRecipe(recipe: Recipe) {
        database.userDao().updateRecipe(recipe)
    }
}