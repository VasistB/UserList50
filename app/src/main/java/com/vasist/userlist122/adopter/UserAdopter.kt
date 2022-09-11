package com.vasist.userlist122.adopter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.vasist.userlist122.JsonData.UserList
import com.vasist.userlist122.R
import com.vasist.userlist122.UserDetails

class UserAdopter(var context: Context, private var userList: MutableList<UserList>):
    RecyclerView.Adapter<UserAdopter.UserViewHolder>(){

    class UserViewHolder(listView:View) :RecyclerView.ViewHolder(listView){
        var name:TextView= listView.findViewById(R.id.idUserName)
        var phone:TextView= listView.findViewById(R.id.idPhoneNumber)
        var email:TextView= listView.findViewById(R.id.idEmail)
        var profile:ImageView= listView.findViewById(R.id.ProfileImageView)
        var process:ProgressBar=listView.findViewById(R.id.idProgress_bar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.user_list_recycler,parent,false)
        return UserViewHolder(view)
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val list = userList[position]

        holder.name.text=list.name.first
        holder.phone.text=list.phone
        holder.email.text=list.email
        holder.profile.load(list.picture.large){
            listener { request, result ->
                holder.process.isGone = true
            }

        }
        holder.itemView.setOnClickListener {
            val i = Intent(context,UserDetails::class.java)
            i.putExtra("item",userList[position])
            context.startActivity(i)
        }
    }
    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateList(list: MutableList<UserList>){
        userList = list
        notifyDataSetChanged()
    }
}

