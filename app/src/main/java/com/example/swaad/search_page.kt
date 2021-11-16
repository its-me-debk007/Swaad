package com.example.swaad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.swaad.ApiRequests.DataClassRestaurantsItem
import com.example.swaad.ApiRequests.RetrofitClient
import com.example.swaad.AuthPages.ForgotPassword2
import com.example.swaad.SearchPage2Files.SearchPage2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

class search_page : Fragment() {
    companion object{
        lateinit var ordering: String
        lateinit var search: String
    }

//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v= inflater.inflate(R.layout.fragment_search_page, container, false)

        val searchBtn  = v.findViewById<TextView>(R.id.searchBtn)
        searchBtn.setOnClickListener {

            val searchView = v.findViewById<SearchView>(R.id.searchBtn)
            search = searchView.query.toString().trim()
            ordering = "-rest_name"

            RetrofitClient.init().getRestaurantName(ordering, search).enqueue(object :
                Callback<List<DataGetRestaurantNames>?> {
                override fun onResponse(
                    call: Call<List<DataGetRestaurantNames>?>,
                    response: Response<List<DataGetRestaurantNames>?>
                ) {
                    response.body()
                    val fragmentManager = activity?.supportFragmentManager
                    val fragmentTransaction = fragmentManager?.beginTransaction()
                    fragmentTransaction?.replace(R.id.fragment_container, SearchPage2())
                    fragmentTransaction?.addToBackStack(null)
                    fragmentTransaction?.commit()

                }

                override fun onFailure(call: Call<List<DataGetRestaurantNames>?>, t: Throwable) {
//                    progressbar.visibility=View.INVISIBLE
                    Toast.makeText(activity,"An error has been occurred\n\nPlease try again", Toast.LENGTH_LONG).show()
                }
            })



        }
        return v
    }

//    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            search_page().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}