package com.example.retrofitrest.utils

class Constants {
    companion object {
        const val BASE_URL =
            "https://raw.githubusercontent.com/Arm63/armen63.io/master/recipe_list/recipes/"
    }

    object DataBase {
        const val RECIPE_DATABASE = "RecipeDB"
        const val RECIPE_TABLE = "RECIPE_TABLE"
    }

    object TransactData {
        const val REQUEST_KEY = "REQUEST_KEY"
        const val BUNDLE_KEY = "BUNDLE_KEY"
        const val RECIPE_TABLE = "RECIPE_TABLE"
    }
}