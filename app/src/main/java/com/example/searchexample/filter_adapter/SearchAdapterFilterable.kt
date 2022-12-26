package com.example.searchexample.filter_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.searchexample.databinding.SearchItemLyBinding
import com.example.searchexample.model.SearchModel
import java.util.*

class SearchAdapterFilterable(var onItemClick: ((SearchModel) -> Unit)? = null) : RecyclerView.Adapter<SearchAdapterFilterable.ItemHolder>() , Filterable {
    inner class ItemHolder(val b: SearchItemLyBinding) : RecyclerView.ViewHolder(b.root)

    var baseList = listOf<SearchModel>()
        set(newList) {
            field = newList
            filterList = newList
            notifyDataSetChanged()
        }
    private var filterList = listOf<SearchModel>()

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

    override fun getFilter(): Filter {
       return object : Filter(){
           override fun performFiltering(constraint: CharSequence?): FilterResults {
               val charString = constraint?.toString() ?: ""
             if (charString.isEmpty()) filterList = baseList else {
                   val filteredList = ArrayList<SearchModel>()
                   baseList.filter { (it.name.contains(constraint!!)) or (it.id.toString().contains(constraint)) }
                       .forEach { filteredList.add(it) }
                   filterList = filteredList

               }
               return FilterResults().apply { values = filterList }

           }

           override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
               filterList = if (results?.values == null)
                   ArrayList()
               else
                   results.values as ArrayList<SearchModel>
               notifyDataSetChanged()
           }
       }
    }
}