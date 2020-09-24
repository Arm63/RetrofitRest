package com.example.retrofitrest.di

import com.example.retrofitrest.ui.RecipeViewModel
import com.example.retrofitrest.ui.adapter.RecipeAdapter
import com.example.retrofitrest.ui.view.fragment.RecipeInfoFragment
import com.example.retrofitrest.ui.view.fragment.RecipeListFragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val fragmentModule = module {
        single {
            RecipeListFragment()
        }
        single {
            RecipeInfoFragment()
        }
    }

    val viewModelModule = module {
        viewModel {
            RecipeViewModel(get())
        }
    }

    val adapterModule = module {
        single {
            RecipeAdapter()
        }

    }
}