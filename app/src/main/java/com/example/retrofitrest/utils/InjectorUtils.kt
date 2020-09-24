package com.example.retrofitrest.utils

import android.content.Context
import com.example.retrofitrest.data.RecipeDatabase
import com.example.retrofitrest.data.api.APIService
import com.example.retrofitrest.data.api.RetrofitInstance
import com.example.retrofitrest.data.repository.implementation.RecipeRepositoryImpl

object InjectorUtils {
    fun provideRepository(context: Context): RecipeRepositoryImpl {
        val db = RecipeDatabase.getDatabase(context)
        val service: APIService = RetrofitInstance.api
        return RecipeRepositoryImpl.getInstance(db, service)
    }
}