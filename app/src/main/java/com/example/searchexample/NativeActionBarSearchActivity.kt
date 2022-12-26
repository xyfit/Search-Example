package com.example.searchexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchexample.databinding.ActivityNativeActionBarSearchBinding
import com.example.searchexample.filter_adapter.SearchAdapter
import java.util.*

class NativeActionBarSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNativeActionBarSearchBinding
    private val searchAdapter: SearchAdapter by lazy { SearchAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNativeActionBarSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)//back buttonni ko'rsatiw
        initRecyclerView()
    }


    private fun initRecyclerView() {
        //recyclerviewg tegiwli kodlar
        binding.recycler1.run {
            layoutManager = LinearLayoutManager(this@NativeActionBarSearchActivity)
            adapter = searchAdapter
        }
        searchAdapter.baseList = StaticList.baseList(this)
        searchAdapter.onItemClick
    }

    /*back buttonni bosiliwini ewitiw, wunchaki override qilinadi*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home->{
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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

            override fun onQueryTextChange(newText: String): Boolean {
                val text = newText.trim().lowercase()
                if (text.isNotEmpty()){//matin bo'w bolmasa
                    val filterResult = StaticList.baseList(this@NativeActionBarSearchActivity)/*StaticList.baseList da asosiy list ni return qiladi*/.filter { it.name.trim().lowercase(
                        Locale.getDefault()).contains(text) || it.id.toString().trim().lowercase(
                        Locale.getDefault()).contains(text)}
                    searchAdapter.baseList = filterResult
                }else{
                    //matin bo'w bo'lsa asosiy listni ko'rsat
                    searchAdapter.baseList = StaticList.baseList(this@NativeActionBarSearchActivity)
                }
               return true
            }
        }

        searchView.run {
            queryHint = "Search.."
            setOnQueryTextListener(searQueryListener)
        }
        return super.onCreateOptionsMenu(menu)

    }
}