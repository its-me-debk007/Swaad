package com.example.swaad.ApiRequests

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.swaad.Manage_Adress
import com.example.swaad.NavBarPages.MyCart
import com.example.swaad.R
import com.example.swaad.RecyclerAdapter
import com.example.swaad.otp_sign_up2
import org.w3c.dom.Text

class RecyclerAdapterManageAddress(val context: Context, val addressData: List<DataClassGetManageAdressItem>): RecyclerView.Adapter<RecyclerAdapterManageAddress.ViewHolder>() {
    companion object
    {
        lateinit var Adress:String
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapterManageAddress.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_card_view_manage_adress,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterManageAddress.ViewHolder, position: Int) {
        holder.manageAdressType.text=addressData[position].address_type
        holder.manageAddress.text=addressData[position].address
        Adress=addressData[position].address
        holder.PhoneNo.text=addressData[position].phone
    }

    override fun getItemCount(): Int {
       return addressData.size
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        var manageAdressType:TextView
        var manageAddress:TextView
        var PhoneNo:TextView
        init {
            manageAdressType=itemView.findViewById(R.id.ManageAddressType)
            manageAddress=itemView.findViewById(R.id.ManageAdress)
            PhoneNo=itemView.findViewById(R.id.PhoneNo)
            itemView.setOnClickListener{
             MyCart.location!!.text= Adress
                Toast.makeText(context, Adress,Toast.LENGTH_LONG).show()
                Toast.makeText(context,"Delievery Location Changed",Toast.LENGTH_LONG).show()
            }
        }
    }
}