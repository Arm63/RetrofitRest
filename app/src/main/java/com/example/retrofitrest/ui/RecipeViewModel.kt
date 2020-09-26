package com.example.retrofitrest.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitrest.data.model.Recipe
import com.example.retrofitrest.data.repository.implementation.RecipeRepositoryImpl
import com.example.retrofitrest.utils.InjectorUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: RecipeRepositoryImpl = InjectorUtils.provideRepository(application)


    fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.load()
        }

    }

    fun getRecipes() = repository.getRecipes()

    fun getRecipe(id: Int) = repository.getRecipe(id)

    fun isDbEmpty() = repository.isDbEmpty()


    fun updateRecipe(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateRecipe(recipe)
        }
    }

    fun deleteRecipe(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRecipe(recipe)
        }
    }

}