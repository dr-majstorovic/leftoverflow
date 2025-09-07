package com.example.leftoverflow.ui.adapter

import android.content.Intent
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.leftoverflow.R
import com.example.leftoverflow.data.model.Meal
import com.example.leftoverflow.ui.recipe.RecipeDetailsActivity

class RecipeAdapter(
    private var recipes: List<Meal>,
    private val onItemClicked: (Meal) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeImage: ImageView = itemView.findViewById(R.id.recipeImage)
        val recipeTitle: TextView = itemView.findViewById(R.id.recipeTitle)
        val recipeCategory: TextView = itemView.findViewById(R.id.recipeCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.recipeTitle.text = recipe.strMeal
        holder.recipeCategory.text = recipe.strCategory ?: ""
        Glide.with(holder.itemView.context)
            .load(recipe.strMealThumb)
            .into(holder.recipeImage)

        holder.itemView.setOnClickListener {
            onItemClicked(recipe)
        }
    }

    fun updateData(newRecipes: List<Meal>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }
}
