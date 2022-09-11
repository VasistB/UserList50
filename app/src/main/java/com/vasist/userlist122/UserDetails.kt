package com.vasist.userlist122


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isGone
import coil.load
import com.vasist.userlist122.JsonData.UserList
import com.vasist.userlist122.databinding.ActivityUserDetailsBinding

class UserDetails : AppCompatActivity() {
    lateinit var binding: ActivityUserDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val user=intent.getSerializableExtra("item") as UserList


        binding.detailUserName.text=user.name.first
        binding.detailUserPhone.text=user.phone
        binding.detailUserEmail.text=user.email
        binding.detailUserGender.text=user.gender
        binding.State.text=user.location.state
        binding.Street.text=user.location.street.name
        binding.city.text=user.location.city
        binding.Country.text=user.location.country
        binding.detailPicture.load(user.picture.large){
            listener { request, result ->
                binding.progress.isGone=true
            }
        }
    }
}