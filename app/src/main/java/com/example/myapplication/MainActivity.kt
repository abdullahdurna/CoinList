package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    private val preferences by lazy {
        getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CoinFragment.newInstance(1))
                .commitNow()
        }
    }
        fun showBinanceCoins(view: View) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CoinFragment.newInstance(1))
                .commit()
        }

        fun markFlaskCoinsAsLoaded() {
            preferences.edit().putBoolean("has_loaded_flask_coins", true).apply()
        }


    fun showFlaskCoins(view: View) {
            val hasLoadedFlaskCoins = preferences.getBoolean("has_loaded_flask_coins", false)
            if (!hasLoadedFlaskCoins) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, FlaskCoinFragment.newInstance(1))
                    .commit()
            }
        }



}

