package com.example.leftoverflow.ui.adapter

import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.leftoverflow.R
import com.example.leftoverflow.data.model.Recipe

class RecipeAdapter(private var recipes: List<Recipe>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeImage: ImageView = itemView.findViewById(R.id.recipeImage)
        val recipeTitle: TextView = itemView.findViewById(R.id.recipeTitle)
        val recipeTime: TextView = itemView.findViewById(R.id.recipeTime)
        val recipeType: TextView = itemView.findViewById(R.id.recipeType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.recipeTitle.text = recipe.title
        holder.recipeTime.text = recipe.time
        holder.recipeType.text = recipe.type
        holder.recipeImage.setImageResource(recipe.imageRes)
    }

    override fun getItemCount(): Int = recipes.size

    fun updateData(newRecipes: List<Recipe>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }
}
