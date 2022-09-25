package com.vasist.userlist122

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vasist.userlist122.JsonData.*
import com.vasist.userlist122.ViewModel.MainViewModel
import com.vasist.userlist122.ViewModel.MainViewModelFactory
import com.vasist.userlist122.adopter.UserAdopter
import com.vasist.userlist122.databinding.ActivityMainBinding
import com.vasist.userlist122.network.RetrofitHelper
import com.vasist.userlist122.repository.QuoteRepository
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
//    private lateinit var newRecyclerView: RecyclerView
    private lateinit var madopter: UserAdopter
    private lateinit var results: MutableList<UserList>
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val quoteService =
            RetrofitHelper.getInstance().create(RetrofitHelper.QuoteService::class.java)
        val repository = QuoteRepository(quoteService)

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
        mainViewModel.quotes.observe(this) {
            results = it.results
            madopter = UserAdopter(this@MainActivity, it.results)
            binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.recyclerView.adapter = madopter
        }

        binding.searchVew2.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                fileList(newText.toString())
                return true
            }
        })
    }

    private fun fileList(filterItem: String) {
        val tempList: MutableList<UserList> = ArrayList()
        for (d in results) {
            if (filterItem in d.name.first.lowercase(Locale.ROOT)) {
                tempList.add(d)
            } else if (filterItem in d.phone) {
                tempList.add(d)
            }
            madopter.updateList(tempList)
        }


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.MenuFemale -> {
                madopter.updateList(results.filter { it.gender=="female" } as MutableList<UserList>)
            }
            R.id.menuMale -> {
                madopter.updateList(results.filter { it.gender=="male" } as MutableList<UserList>)
            }
            R.id.MenuClearFilter -> {
                madopter.updateList(results)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}



