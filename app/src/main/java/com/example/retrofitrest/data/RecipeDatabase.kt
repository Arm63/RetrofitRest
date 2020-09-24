package com.example.retrofitrest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.retrofitrest.data.dao.RecipeDao
import com.example.retrofitrest.data.model.Recipe
import com.example.retrofitrest.utils.Constants.DataBase.RECIPE_DATABASE


@Database(entities = [Recipe::class], version = 5, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun userDao(): RecipeDao

    companion object {
//        private val MIGRATION_2_4: Migration = object : Migration(2, 4) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL(
//                    "ALTER TABLE 'RECIPE_TABLE' ADD COLUMN 'description' TEXT NOT NUll default 1"
//                )
//            }
//        }

        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getDatabase(context: Context): RecipeDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    RECIPE_DATABASE
                )
//                    .addMigrations(MIGRATION_2_4)
                    .build()
                INSTANCE = instance

                return instance
            }
        }
    }
}