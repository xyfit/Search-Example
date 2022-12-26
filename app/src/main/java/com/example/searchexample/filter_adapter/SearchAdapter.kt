package com.example.searchexample.filter_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.searchexample.databinding.SearchItemLyBinding
import com.example.searchexample.model.SearchModel
import java.util.*

class SearchAdapter(var onItemClick: ((SearchModel) -> Unit)? = null): RecyclerView.Adapter<SearchAdapter.ItemHolder>(){
    inner class ItemHolder(val b: SearchItemLyBinding) : RecyclerView.ViewHolder(b.root)

    var baseList = listOf<SearchModel>()
        set(newList) {
            field = newList
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            SearchItemLyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val itemDate = baseList[position]
        holder.b.name.text = itemDate.name
        holder.b.nameId.text = itemDate.id.toString()
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(itemDate)
        }
    }

    override fun getItemCount(): Int = baseList.size
}