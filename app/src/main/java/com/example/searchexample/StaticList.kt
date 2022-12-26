package com.example.searchexample

import android.content.Context
import com.example.searchexample.model.SearchModel

object StaticList {
    fun baseList(context: Context): List<SearchModel>{
//        val names = listOf<String>(
//            "Theodore",
//            "Jack",
//            "Levi",
//            "Alexander",
//            "Jackson",
//            "Mateo",
//            "Daniel",
//            "Michael",
//            "Mason",
//            "Sebastian",
//            "Ethan",
//            "Logan",
//            "Owen",
//            "Samuel",
//            "Jacob",
//            "Asher",
//            "Aiden",
//            "John",
//            "Joseph",
//            "Wyatt",
//            "David"
//        )
        val names = context.resources.getStringArray(R.array.top_english_names_array)
        val searchList = arrayListOf<SearchModel>()
        names.forEachIndexed { index, s ->
            searchList.add(SearchModel(index+1, s))
        }
        return searchList
    }
}