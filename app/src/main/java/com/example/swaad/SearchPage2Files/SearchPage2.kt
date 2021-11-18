package com.example.swaad.SearchPage2Files

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swaad.ApiRequests.DataClassRestaurantsItem
import com.example.swaad.ApiRequests.RetrofitClient
import com.example.swaad.DataGetRestaurantNames
import com.example.swaad.R
import com.example.swaad.RecyclerAdapter
import com.example.swaad.SearchPage2Files.RecyclerAdapterSearchPage
import com.example.swaad.search_page
import com.example.swaad.search_page.Companion.ordering
import com.example.swaad.search_page.Companion.search
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class SearchPage2: Fragment() {
    private var layoutManager: RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<RecyclerAdapterSearchPage.ViewHolder>? = null
    lateinit var responseBody:List<DataGetRestaurantNames>
//    lateinit var responseBody:List<DataGetRestaurantNames>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v= inflater.inflate(R.layout.search_page_2, container, false)
        val progressBar=v.findViewById<ProgressBar>(R.id.progressBarSearchPage)
        progressBar.visibility=View.VISIBLE


        RetrofitClient.init().getRestaurantName(ordering, search).enqueue(object :
            Callback<List<DataGetRestaurantNames>?> {
            override fun onResponse(
                call: Call<List<DataGetRestaurantNames>?>,
                response: Response<List<DataGetRestaurantNames>?>
            ) {
                try {
                    responseBody = response.body()!!
                    if (container != null) {
                        progressBar.visibility = View.INVISIBLE
                        layoutManager = LinearLayoutManager(container?.context)
                        val recyclerViewSearchPage2 =
                            v.findViewById<RecyclerView>(R.id.recyclerViewSearchPage2)
                        recyclerViewSearchPage2.layoutManager = layoutManager

                        adapter = RecyclerAdapterSearchPage(container.context, responseBody)
                        recyclerViewSearchPage2.adapter = adapter
                    }
                }
                catch (e: Exception){
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(activity,"Oops!", Toast.LENGTH_LONG).show()
                }

            }
            override fun onFailure(call: Call<List<DataGetRestaurantNames>?>, t: Throwable) {
                Toast.makeText(activity,"An error has been occurred\n\nPlease try again", Toast.LENGTH_LONG).show()
            }
        })


        val clear = v.findViewById<ImageView>(R.id.clear)
        clear.setOnClickListener {
            v.findViewById<EditText>(R.id.searchBar).text.clear()
        }

        return v
    }
}