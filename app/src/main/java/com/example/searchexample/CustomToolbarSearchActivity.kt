package com.example.searchexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.google.android.material.appbar.MaterialToolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchexample.databinding.ActivityCustomSearchBinding
import com.example.searchexample.filter_adapter.SearchAdapterFilterable


class CustomToolbarSearchActivity : AppCompatActivity() {
    private val TAG = "onQueryTextChangeTag"
    private lateinit var binding: ActivityCustomSearchBinding
    private val searchAdapterFilterable: SearchAdapterFilterable by lazy {
        SearchAdapterFilterable() {
            Toast.makeText(this, "${it.name}", Toast.LENGTH_SHORT).show()
        }
    }


    //TODO Theme.MaterialComponents.NoActionBar bo'liwi kk


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.materialToolbar)//o'zimizni toolbarimizni joylemiz

        initRecyclerView()
        binding.materialToolbar.setNavigationOnClickListener { finish() }

    }

    private fun initRecyclerView() {
        //recyclerviewg tegiwli kodlar
        binding.recycler3.run {
            layoutManager = LinearLayoutManager(this@CustomToolbarSearchActivity)
            binding.recycler3.adapter = searchAdapterFilterable
            searchAdapterFilterable.baseList = StaticList.baseList(this@CustomToolbarSearchActivity)
        }
        searchAdapterFilterable.baseList = StaticList.baseList(this)
    }
    /*menu briktiruw, wunchaki override qilinadi */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val  search = menu.findItem(R.id.app_search_bar)//menudagi id
        val searchView = search.actionView as SearchView

        val searQueryListener = object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchAdapterFilterable.filter.filter(newText)
                Log.d(TAG, "$newText")
                return false
            }
        }

        searchView.run {
            queryHint = "Search.."
            setOnQueryTextListener(searQueryListener)
        }
        return super.onCreateOptionsMenu(menu)

    }
}