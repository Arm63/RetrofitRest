package com.example.retrofitrest.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.retrofitrest.data.model.Recipe


@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: List<Recipe>)

    @Query("SELECT * FROM RECIPE_TABLE")
    fun getAllData(): LiveData<List<Recipe>>

    @Query("SELECT * FROM RECIPE_TABLE WHERE id = :id")
    fun getItem(id: Int): LiveData<Recipe>

    @Query("SELECT COUNT(id) FROM RECIPE_TABLE")
    fun isDbEmpty(): LiveData<Int>

    @Update
    suspend fun updateRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

}