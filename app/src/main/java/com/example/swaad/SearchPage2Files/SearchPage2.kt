package com.example.swaad.SearchPage2Files

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swaad.R
import com.example.swaad.SearchPage2Files.RecyclerAdapterSearchPage

class SearchPage2: Fragment() {
    private var layoutManager: RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<RecyclerAdapterSearchPage.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v= inflater.inflate(R.layout.search_page_2, container, false)

        layoutManager = LinearLayoutManager(container?.context)
        val recyclerViewSearchPage2 = v.findViewById<RecyclerView>(R.id.recyclerViewSearchPage2)
        recyclerViewSearchPage2.layoutManager = layoutManager
        adapter = RecyclerAdapterSearchPage()
        recyclerViewSearchPage2.adapter = adapter

        val clear = v.findViewById<ImageView>(R.id.clear)
        clear.setOnClickListener{
            v.findViewById<EditText>(R.id.searchBar).text.clear()
            }

        return v
    }
}