package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class FlaskCoin(
    @SerializedName("symbol") val name : String,
    @SerializedName("rsi") val price: String
)
