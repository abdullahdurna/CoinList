package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

/**
 * A fragment representing a list of Items.
 */
class CoinFragment : Fragment() {

    private var columnCount = 1
    private lateinit var adapter: MyItemRecyclerViewAdapter
    private val viewModel: CoinListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_coin_list, container, false)

        // Set the adapter
        adapter = MyItemRecyclerViewAdapter(listOf())
        val recyclerView = view.findViewById<RecyclerView>(R.id.list)
        adapter = MyItemRecyclerViewAdapter(listOf())
        recyclerView.layoutManager = when {
            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }
        recyclerView.adapter = adapter

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.coins.observe(viewLifecycleOwner, { coinList ->
            if (coinList != null && coinList.isNotEmpty()) {
                Log.d("FRAGMENT_OBSERVE", "Coin list observed with size: ${coinList.size}")
                adapter.updateData(coinList)
            } else {
                Log.d("FRAGMENT_OBSERVE", "Coin list is empty or null.")
            }
        })



    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            CoinFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}

