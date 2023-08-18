package api

import com.example.myapplication.Coin
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("api/v3/ticker/price")
    suspend fun getAllCoins(): Response<List<Coin>>

    @GET("getOversoldCoins/1d")
    suspend fun getFlaskCoins(): Response<List<Coin>>



}

