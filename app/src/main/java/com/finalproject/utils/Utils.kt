package com.finalproject.utils

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.view.Menu
import android.view.View
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.core.widget.TextViewCompat
import com.finalproject.R
import org.jetbrains.anko.backgroundColor


fun Activity.createSearchViewMenu(menu: Menu?, listener: (String) -> Unit) {
    menuInflater.inflate(R.menu.search_menu, menu)
    val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
    val searchView = (menu?.findItem(R.id.search)?.actionView as SearchView).apply {
        this.backgroundColor = Color.WHITE
        setSearchableInfo(searchManager.getSearchableInfo(componentName))
    }
    val searchEditText = searchView.findViewById<View>(R.id.search_src_text) as EditText
    TextViewCompat.setTextAppearance(searchEditText, R.style.TextView_BlackTwo_Search)

    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let { listener.invoke(it) }
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    })
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}