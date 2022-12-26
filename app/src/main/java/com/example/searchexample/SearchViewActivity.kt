package com.example.searchexample

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchexample.databinding.ActivitySearchViewBinding
import com.example.searchexample.filter_adapter.SearchAdapter
import java.util.*

class SearchViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchViewBinding
    private val searchAdapter: SearchAdapter by lazy { SearchAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)//back buttonni ko'rsatiw

        initRecyclerView()
        initSearchView()
    }

    private fun initSearchView() {
        val queryTextListener = object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                val text = newText.trim().lowercase()
                if (text.isNotEmpty()){//matin bo'w bolmasa
                    val filterResult = StaticList.baseList(this@SearchViewActivity)/*StaticList.baseList da asosiy list ni return qiladi*/.filter { it.name.trim().lowercase(
                        Locale.getDefault()).contains(text) || it.id.toString().trim().lowercase(
                        Locale.getDefault()).contains(text)}
                    searchAdapter.baseList = filterResult
                }else{
                    //matin bo'w bo'lsa asosiy listni ko'rsat
                    searchAdapter.baseList = StaticList.baseList(this@SearchViewActivity)
                }

                return false
            }
        }
        binding.searchV.setOnQueryTextListener(queryTextListener)//searchviewni queryTextListener ga berilvoti
    }

    private fun initRecyclerView() {
        //recyclerviewg tegiwli kodlar
        binding.recycler2.run {
            layoutManager = LinearLayoutManager(this@SearchViewActivity)
            adapter = searchAdapter
        }
        searchAdapter.baseList = StaticList.baseList(this)
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
}