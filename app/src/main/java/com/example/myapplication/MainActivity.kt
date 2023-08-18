package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
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

        fun showFlaskCoins(view: View) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FlaskCoinFragment.newInstance(1))
                .commit()
        }

}

