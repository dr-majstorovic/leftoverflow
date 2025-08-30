package com.example.leftoverflow.ui

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leftoverflow.R
import com.example.leftoverflow.data.model.Recipe
import com.example.leftoverflow.ui.adapter.RecipeAdapter

class SearchActivity : ComponentActivity() {

    private lateinit var searchInput: EditText
    private lateinit var resultsRecycler: RecyclerView
    private lateinit var adapter: RecipeAdapter

    private val allRecipes = listOf(
        Recipe("Pasta Carbonara", "25 min", "Glavno jelo", R.drawable.pasta),
        Recipe("Čokoladni kolač", "45 min", "Desert", R.drawable.cake),
        Recipe("Grčka salata", "15 min", "Predjelo", R.drawable.salad)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchInput = findViewById(R.id.searchInput)
        resultsRecycler = findViewById(R.id.resultsRecycler)

        adapter = RecipeAdapter(emptyList())
        resultsRecycler.layoutManager = LinearLayoutManager(this)
        resultsRecycler.adapter = adapter

        searchInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = searchInput.text.toString().trim()
                performSearch(query)
                true
            } else {
                false
            }
        }
    }

    private fun performSearch(query: String) {
        val results = allRecipes.filter {
            it.title.contains(query, ignoreCase = true)
        }
        adapter.updateData(results)
    }
}
