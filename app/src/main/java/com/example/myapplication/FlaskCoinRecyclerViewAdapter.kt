package com.example.myapplication
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class FlaskCoinRecyclerViewAdapter(
    private var values: List<FlaskCoin>
) : RecyclerView.Adapter<FlaskCoinRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.content)
        val priceView: TextView = view.findViewById(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_coin, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.textView.text = item.name
        holder.priceView.text = item.price
    }

    override fun getItemCount(): Int = values.size

    fun updateData(newData: List<FlaskCoin>) {
        this.values = newData
        notifyDataSetChanged()
    }
}
