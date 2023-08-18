package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.FlaskCoinListViewModel
import com.example.myapplication.MyItemRecyclerViewAdapter
import com.example.myapplication.R

class FlaskCoinFragment : Fragment() {

    private var columnCount = 1
    private lateinit var adapter: MyItemRecyclerViewAdapter
    private val viewModel: FlaskCoinListViewModel by viewModels()

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
        val recyclerView = view.findViewById<RecyclerView>(R.id.liste)
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
                adapter.updateData(coinList)
            }
        })
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
        @JvmStatic
        fun newInstance(columnCount: Int) =
            FlaskCoinFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
