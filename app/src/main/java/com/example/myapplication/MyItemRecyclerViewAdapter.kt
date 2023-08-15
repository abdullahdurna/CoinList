package com.example.myapplication

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.myapplication.placeholder.PlaceholderContent.PlaceholderItem
import com.example.myapplication.databinding.FragmentCoinBinding


class MyItemRecyclerViewAdapter(
    private var values: List<Coin>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.content)
        val priceView: TextView = view.findViewById(R.id.price)  // Yeni eklediğimiz kısım
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_coin, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        Log.d("ADAPTER_BIND", "Binding data at position: $position, name: ${item.name}")
        holder.textView.text = item.name
        holder.priceView.text = item.price  // Fiyat bilgisini set ediyoruz
    }


    override fun getItemCount(): Int = values.size

    fun updateData(newData: List<Coin>) {
        this.values = newData
        Log.d("ADAPTER_UPDATE", "Data updated in adapter with size: ${newData.size}")
        notifyDataSetChanged()
    }

}
