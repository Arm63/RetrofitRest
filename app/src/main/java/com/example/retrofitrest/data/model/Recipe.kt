package com.example.retrofitrest.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.retrofitrest.utils.Constants.DataBase.RECIPE_TABLE
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = RECIPE_TABLE)
data class Recipe(

	@PrimaryKey
	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("price")
	var price: Int,

	@field:SerializedName("name")
	var name: String,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("from_user")
	val fromUser: Boolean
) : Parcelable
