package com.example.myapplication

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class CoinListViewModel : ViewModel() {

    private val _coins = MutableLiveData<List<Coin>>()
    val coins: LiveData<List<Coin>> get() = _coins

    init {
        getAllCoins()
    }

    private fun getAllCoins() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitInstance.apiService.getAllCoins()
            Log.d("API_RESPONSE", "Response: ${response.body()}")  // Bu logu ekleyin
            if (response.isSuccessful) {
                _coins.postValue(response.body())
            } else {
                Log.d("API_ERROR", "Response Code: ${response.code()} - Message: ${response.message()}")
            }
        }
    }


}

