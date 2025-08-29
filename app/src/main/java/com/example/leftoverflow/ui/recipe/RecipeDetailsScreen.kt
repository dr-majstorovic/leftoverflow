package com.example.leftoverflow.ui.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.leftoverflow.R

@Composable
fun RecipeDetailsScreen(
    recipeTitle: String,
    recipeType: String,
    prepTime: String,
    ingredients: List<String>,
    steps: List<String>,
    recipeImageRes: Int
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Top bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.leftoverflow),
                contentDescription = "App Logo",
                modifier = Modifier.size(48.dp)
            )
            Row {
                IconButton(onClick = { /* share action */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.share),
                        contentDescription = "Share",
                        modifier = Modifier
                    )
                }
                IconButton(onClick = { /* favourite toggle */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.star1),
                        contentDescription = "Favourite"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Recept image
        Image(
            painter = painterResource(id = recipeImageRes),
            contentDescription = "Recipe Image",
            modifier = Modifier
                .fillMaxWidth()
                //.height(200.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Naslov, tip, vrijeme pripreme
        Text(text = recipeTitle, style = MaterialTheme.typography.headlineSmall)
        Text(text = "$recipeType • $prepTime", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Sastojci
        Text(text = "Ingredients", style = MaterialTheme.typography.titleMedium)
        ingredients.forEach { ingredient ->
            Text(text = "• $ingredient", style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Priprema
        Text(text = "Preparation", style = MaterialTheme.typography.titleMedium)
        steps.forEachIndexed { index, step ->
            Text(text = "${index + 1}. $step", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecipeDetailsScreenPreview() {
    RecipeDetailsScreen(
        recipeTitle = "Chocolate Cake",
        recipeType = "Dessert",
        prepTime = "45 min",
        ingredients = listOf("2 eggs", "100g sugar", "200g flour", "50g cocoa powder"),
        steps = listOf(
            "Preheat oven to 180°C.",
            "Mix dry ingredients.",
            "Add eggs and stir well.",
            "Pour into baking pan and bake for 30 minutes."
        ),
        recipeImageRes = R.drawable.mugcake
    )
}