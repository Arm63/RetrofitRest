package com.example.retrofitrest.data.api

import com.example.retrofitrest.data.model.Recipe
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("recipes.json")
    suspend fun getRecipes(): Response<List<Recipe>>
}