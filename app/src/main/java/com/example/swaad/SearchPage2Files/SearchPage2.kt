package com.example.swaad.SearchPage2Files

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swaad.*
import com.example.swaad.ApiRequests.DataGetDishesList
import com.example.swaad.ApiRequests.RetrofitClient
import com.example.swaad.NavBarPages.Home_page
import com.example.swaad.NavBarPages.Home_page.Companion.food
import com.example.swaad.NavBarPages.Home_page.Companion.responseDataKunal
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class SearchPage2: Fragment() {
    private var layoutManager: RecyclerView.LayoutManager?=null
    private var adapter: RecyclerView.Adapter<RecyclerAdapterSearchPage.ViewHolder>? = null


    companion object{
        lateinit var responseDataDebashish: List<DataGetDishesList>
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v= inflater.inflate(R.layout.search_page_2, container, false)
        val progressBar=v.findViewById<ProgressBar>(R.id.progressBarSearchPage)

        RetrofitClient.init().categoryDishSweet(food).enqueue(object :
            Callback<List<DataGetDishesList>?> {
            override fun onResponse(
                call: Call<List<DataGetDishesList>?>,
                response: Response<List<DataGetDishesList>?>
            ) {
                try {
                    responseDataDebashish = response.body()!!
                    if (container != null) {
                        progressBar.visibility = View.INVISIBLE
                        layoutManager = LinearLayoutManager(container?.context)
                        val recyclerViewSearchPage2 =
                            v.findViewById<RecyclerView>(R.id.recyclerViewSearchPage2)
                        recyclerViewSearchPage2.layoutManager = layoutManager

                        adapter = RecyclerAdapterSearchPage(container.context, responseDataDebashish)
                        recyclerViewSearchPage2.adapter = adapter
                    }
                } catch (e: Exception) {
                    progressBar.visibility = View.INVISIBLE
                    Toast.makeText(activity, "Oops! The result cannot be loaded", Toast.LENGTH_LONG).show()
                }

            }

            override fun onFailure(call: Call<List<DataGetDishesList>?>, t: Throwable) {
                Toast.makeText(
                    activity,
                    "An error has been occurred\n\nPlease try again",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        val searchText = v.findViewById<EditText>(R.id.searchBar)
        searchText.requestFocus()
//        searchText.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if(s.toString().isNotEmpty()){
//                    RetrofitClient.init().getRestaurantName("-dish_name", s.toString()).enqueue(object :
//                        Callback<List<DataGetDishesList>?> {
//                        override fun onResponse(
//                            call: Call<List<DataGetDishesList>?>,
//                            response: Response<List<DataGetDishesList>?>
//                        ) {
//                            try {
//                                responseDataDebashish = response.body()!!
//                                if (container != null) {
//                                    progressBar.visibility = View.INVISIBLE
//                                    layoutManager = LinearLayoutManager(container?.context)
//                                    val recyclerViewSearchPage2 =
//                                        v.findViewById<RecyclerView>(R.id.recyclerViewSearchPage2)
//                                    recyclerViewSearchPage2.layoutManager = layoutManager
//
//                                    adapter = RecyclerAdapterSearchPage(container.context, responseDataDebashish)
//                                    recyclerViewSearchPage2.adapter = adapter
//                                }
//                            } catch (e: Exception) {
//                                progressBar.visibility = View.INVISIBLE
//                                Toast.makeText(activity, "Oops! The search result cannot be loaded", Toast.LENGTH_LONG).show()
//                            }
//
//                        }
//
//                        override fun onFailure(call: Call<List<DataGetDishesList>?>, t: Throwable) {
//                            Toast.makeText(
//                                activity,
//                                "An error has been occurred\n\nPlease try again",
//                                Toast.LENGTH_LONG
//                            ).show()
//                        }
//                    })
//                }
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//                TODO("Not yet implemented")
//            }
//        })

        val searchBtn  = v.findViewById<ImageView>(R.id.searchBtn)
        searchBtn.setOnClickListener {
            progressBar.visibility=View.VISIBLE

            var search = searchText.text.toString().trim()

            RetrofitClient.init().getRestaurantName("-dish_name", search).enqueue(object :
                Callback<List<DataGetDishesList>?> {
                override fun onResponse(
                    call: Call<List<DataGetDishesList>?>,
                    response: Response<List<DataGetDishesList>?>
                ) {
                    try {
                        responseDataDebashish = response.body()!!
                        if (container != null) {
                            progressBar.visibility = View.INVISIBLE
                            layoutManager = LinearLayoutManager(container?.context)
                            val recyclerViewSearchPage2 =
                                v.findViewById<RecyclerView>(R.id.recyclerViewSearchPage2)
                            recyclerViewSearchPage2.layoutManager = layoutManager

                            adapter = RecyclerAdapterSearchPage(container.context, responseDataDebashish)
                            recyclerViewSearchPage2.adapter = adapter
                        }
                    } catch (e: Exception) {
                        progressBar.visibility = View.INVISIBLE
                        Toast.makeText(activity, "Oops! The search result cannot be loaded", Toast.LENGTH_LONG).show()
                    }

                }

                override fun onFailure(call: Call<List<DataGetDishesList>?>, t: Throwable) {
                    Toast.makeText(
                        activity,
                        "An error has been occurred\n\nPlease try again",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }


//        val clear = v.findViewById<ImageView>(R.id.clear)
//        clear.setOnClickListener {
//            v.findViewById<EditText>(R.id.searchBar).text.clear()
//        }

        val backBtn = v.findViewById<ImageView>(R.id.backwardBtn)
        backBtn.setOnClickListener{
            val fragmentManager = activity?.supportFragmentManager
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(com.example.swaad.R.id.fragment_container, Home_page())
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
        }

        return v
    }
}