package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class Coin(
    @SerializedName("symbol") val name : String,
    @SerializedName("price") val price: String
)

