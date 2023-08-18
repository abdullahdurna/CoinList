package com.example.myapplication

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.concurrent.timerTask
import java.util.Timer

class FlaskCoinListViewModel : ViewModel() {

    private val _coins = MutableLiveData<List<Coin>>()
    val coins: LiveData<List<Coin>> get() = _coins

    private val timer = Timer()

    init {
        timer.scheduleAtFixedRate(timerTask { getAllCoins() },0,5000)
    }

    private fun getAllCoins() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitInstance.apiServiceFlask.getFlaskCoins()
            if (response.isSuccessful) {
                _coins.postValue(response.body() ?: listOf())
            } else {
                Log.d("FLASK_API_ERROR", "Response Code: ${response.code()} - Message: ${response.message()}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}