
package com.example.swaad

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.swaad.ApiRequests.JsonConverterAddAdress
import com.example.swaad.ApiRequests.RetrofitClient
import com.example.swaad.AuthPages.FragmentLogIn
import com.example.swaad.SplashScreen.Splash_screen
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddingAdress : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_adding_adress, container, false)
        var saveandcontinue=v.findViewById<Button>(R.id.SaveAndContinue)
        var flag =0
        var adressType:String? =null
        var home = v.findViewById<TextView>(R.id.addAdressHome)
        var work=v.findViewById<TextView>(R.id.addAdressWork)
        var other=v.findViewById<TextView>(R.id.addAdressOther)
        home.setOnClickListener {
            Toast.makeText(container?.context,"The address will saved as Home",Toast.LENGTH_LONG).show()
            flag =1
            adressType="Home"
        }
        work.setOnClickListener {
            Toast.makeText(container?.context,"The address will saved as Work",Toast.LENGTH_LONG).show()
            flag =1
            adressType="Work"
        }
        other.setOnClickListener {
            Toast.makeText(container?.context,"The address will saved as Other",Toast.LENGTH_LONG).show()
            flag =1
            adressType="Other"
        }
        saveandcontinue.setOnClickListener {

//            var adressType:String? =null
            var houseNo=v.findViewById<EditText>(R.id.HouseNo).text.toString()
            var  PhoneNo = v.findViewById<EditText>(R.id.PhoneNoManageAdress).text.toString()
//            var home = v.findViewById<TextView>(R.id.addAdressHome)
//            var work=v.findViewById<TextView>(R.id.addAdressWork)
//            var other=v.findViewById<TextView>(R.id.addAdressOther)
            if(houseNo.isEmpty())
            {
                Toast.makeText(container?.context,"Please Enter Your House No.",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(PhoneNo.isEmpty())
            {
                    Toast.makeText(container?.context,"Please Enter Your Phone No.",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
//            home.setOnClickListener {
//                Toast.makeText(container?.context,"Home",Toast.LENGTH_LONG).show()
//                flag =1
//                adressType="Home"
//            }
//            work.setOnClickListener {
//                Toast.makeText(container?.context,"Work",Toast.LENGTH_LONG).show()
//                flag =1
//                adressType="Work"
//            }
//            other.setOnClickListener {
//                Toast.makeText(container?.context,"OTher",Toast.LENGTH_LONG).show()
//                flag =1
//                adressType="Other"
//            }
            if(flag ==0)
            {
                Toast.makeText(container?.context,"Please Select Save Adress Type",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            var apartment=v.findViewById<EditText>(R.id.Apartment).text.toString()
            var Direction = v.findViewById<EditText>(R.id.DirectionToReach).text.toString()
            var Adress=houseNo+apartment+Direction
            var jsonConverterAddAdress=JsonConverterAddAdress(PhoneNo,Adress, adressType.toString())

            var Token:String="asdasdas"
            lifecycleScope.launch{
               var AccessToken = Splash_screen.readInfo("accessToken").toString()
                Token =AccessToken
            }
            RetrofitClient.init().addAddress(jsonConverterAddAdress,token="Bearer $Token").enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    if(response.isSuccessful())
                    {
                        Toast.makeText(container?.context,"Address Saved Successfully",Toast.LENGTH_LONG).show()
                    }
                   else
                   {
                       Toast.makeText(container?.context,response.code().toString(),Toast.LENGTH_LONG).show()
                   }
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })

        }
        return v
    }
}