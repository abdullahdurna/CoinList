package com.example.myapplication

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.concurrent.timerTask
import java.util.Timer


class CoinListViewModel : ViewModel() {

    private val _coins = MutableLiveData<List<Coin>>()
    val coins: LiveData<List<Coin>> get() = _coins
    private val timer = Timer()
    init {
        timer.scheduleAtFixedRate(timerTask { getAllCoins() }, 0, 1000)
    }
    private fun getAllCoins() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitInstance.apiServiceBinance.getAllCoins()
            if (response.isSuccessful) {
                val allCoins = response.body() ?: listOf() // Eğer null ise boş liste atıyoruz
                val filteredCoins = allCoins.filter { it.name.endsWith("USDT") }
                _coins.postValue(filteredCoins)
            } else {
                Log.d("API_ERROR", "Response Code: ${response.code()} - Message: ${response.message()}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()  // ViewModel temizlenirken timer'ı iptal ediyoruz
    }
}


