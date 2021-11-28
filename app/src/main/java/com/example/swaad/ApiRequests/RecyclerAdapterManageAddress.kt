package com.example.swaad.ApiRequests

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.swaad.*
import com.example.swaad.AuthPages.ForgotPassword2
import com.example.swaad.NavBarPages.MyCart
import org.w3c.dom.Text

class RecyclerAdapterManageAddress(val context: Context, val addressData: List<DataClassGetManageAdressItem>): RecyclerView.Adapter<RecyclerAdapterManageAddress.ViewHolder>() {
    companion object
    {
        lateinit var Adress:String
        var flag =0
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterManageAddress.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_view_manage_adress,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterManageAddress.ViewHolder, position: Int) {
        holder.manageAdressType.text=addressData[position].address_type
        holder.manageAddress.text=addressData[position].address

        holder.PhoneNo.text=addressData[position].phone
        holder.AddingAdress.setOnClickListener {
            flag = 1
            Adress=addressData[position].address
            Toast.makeText(context,"Delivery Adress Changed",Toast.LENGTH_LONG ).show()
//            val fragmentManager = NavBarActivity()?.supportFragmentManager
//            val fragmentTransaction = fragmentManager?.beginTransaction()
//            fragmentTransaction?.replace(R.id.fragment_container, MyCart())
//            fragmentTransaction?.addToBackStack("fragmentLogin")
//            fragmentTransaction?.commit()
//            MyCart.location?.text= Adress
        }

    }

    override fun getItemCount(): Int {
       return addressData.size
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        var manageAdressType:TextView
        var manageAddress:TextView
        var PhoneNo:TextView
        var AddingAdress: Button
        init {
            manageAdressType=itemView.findViewById(R.id.ManageAddressType)
            manageAddress=itemView.findViewById(R.id.ManageAdress)
            PhoneNo=itemView.findViewById(R.id.PhoneNo)
            AddingAdress=itemView.findViewById(R.id.addAdress)
        }
    }
}